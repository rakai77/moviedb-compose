package com.example.main.feature_home.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.ArrowForwardIos
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.common_ui.theme.BlueAccent
import com.example.common_ui.theme.DarkGrey
import com.example.common_ui.theme.Orange
import com.example.common_ui.theme.WhiteGrey
import com.example.common_ui.utils.setImage
import com.example.common_ui.utils.shimmerEffect
import com.example.common_ui.utils.showShimmer
import com.example.core.domain.model.AllTrendingItem
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController
) {

    val hazeState = remember { HazeState() }
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val homeViewModel: HomeViewModel = koinViewModel()
    val trendingState = homeViewModel.trendingState.collectAsState()
    val moviePopState = homeViewModel.moviePopState.collectAsState()
    val onAirSeriesState = homeViewModel.onAirSeriesState.collectAsState()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text("") },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    scrolledContainerColor = Color.Transparent
                ),
                windowInsets = WindowInsets.statusBars,
                navigationIcon = {
                    Icon(
                        modifier = Modifier.size(96.dp),
                        painter = painterResource(id = com.example.common_ui.R.drawable.ic_logo),
                        contentDescription = "Icon Content",
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Rounded.Notifications,
                            contentDescription = "Notification Menu",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Rounded.Settings,
                            contentDescription = "Settings Menu",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                when (trendingState.value) {
                    is HomeUiState.LoadingTrending -> {
                        ShimmerSection()
                    }
                    is HomeUiState.SuccessLoadTrending -> {
                        val trending = (trendingState.value as HomeUiState.SuccessLoadTrending).trending
                        TrendingSliderSection(
                            hazeState = hazeState,
                            allTrendingList = trending.results.take(10)
                        )
                    }
                    is HomeUiState.ErrorTrending -> {
                        val errorMessage = (trendingState.value as HomeUiState.ErrorTrending).errorMessage
                        Toast.makeText(LocalContext.current, "message: $errorMessage", Toast.LENGTH_SHORT).show()
                        Log.d("cek error", "HomeScreen: $errorMessage")
                    }
                    else -> Unit
                }
            }

            if (moviePopState.value is HomeUiState.SuccessLoadMoviePop) {
                val state = moviePopState.value as HomeUiState.SuccessLoadMoviePop
                item {
                    SectionTitle(
                        title = stringResource(id = com.example.common_ui.R.string.movie_popular_label),
                        itemSize = state.movie.results.size
                    ) { }
                }

                val movies = state.movie.results.take(10)
                items(movies.chunked(2)) { rowMovies ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        for (movie in rowMovies) {
                            MovieItem(
                                thumbnailUrl = movie.posterPath.setImage(),
                                rating = movie.voteAverage.toString(),
                                modifier = Modifier
                                    .weight(1f)
                                    .aspectRatio(2f / 3f)
                            )
                        }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
            }

            if (onAirSeriesState.value is HomeUiState.SuccessOnAirSeries) {
                val state = onAirSeriesState.value as HomeUiState.SuccessOnAirSeries
                item {
                    SectionTitle(
                        title = stringResource(id = com.example.common_ui.R.string.on_air_series_label),
                        itemSize = state.series.results.size
                    ) { }
                }

                val series = state.series.results.take(10)
                items(series.chunked(2)) { rowSeries ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        for (item in rowSeries) {
                            MovieItem(
                                thumbnailUrl = item.posterPath.setImage(),
                                rating = item.voteAverage.toString(),
                                modifier = Modifier
                                    .weight(1f)
                                    .aspectRatio(2f / 3f)
                            )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}

@Composable
fun TrendingSliderSection(
    hazeState: HazeState,
    allTrendingList: List<AllTrendingItem>
) {
    val pagerState = rememberPagerState {
        allTrendingList.size
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        pageSpacing = 8.dp,
        contentPadding = PaddingValues(16.dp)
    ) { page ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(size = 8.dp))
                    .haze(hazeState),
                contentScale = ContentScale.Crop,
                model = allTrendingList[page].backdropPath.setImage(),
                contentDescription = null
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = if (allTrendingList[page].mediaType == "tv") {
                        allTrendingList[page].name ?: ""
                    } else allTrendingList[page].title ?: "",
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = if (allTrendingList[page].mediaType == "tv") {
                        allTrendingList[page].firstAirDate ?: ""
                    } else allTrendingList[page].releaseDate ?: "",
                    color = Color.White,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }

    Row(
        Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration) {
                BlueAccent
            } else DarkGrey
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(8.dp)
            )
        }
    }
}

@Composable
fun SectionTitle(
    modifier: Modifier = Modifier,
    title: String,
    itemSize: Int,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
        )
        if (itemSize >= 10) {
            IconButton(
                onClick = onClick
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Rounded.ArrowForwardIos,
                    contentDescription = "See More",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

@Composable
fun MovieItem(
    thumbnailUrl: String,
    rating: String,
    modifier: Modifier
) {

    var showShimmer by remember {
        mutableStateOf(true)
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
    ) {
        AsyncImage(
            model = thumbnailUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .clip(RoundedCornerShape(12.dp))
                .showShimmer(showShimmer = showShimmer),
            onSuccess = { showShimmer = false}
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF2D3E50).copy(alpha = 0.8f))
                .padding(horizontal = 8.dp, vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = Icons.Filled.Star,
                    tint = Orange,
                    contentDescription = "Icon Ratting"
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = rating,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = WhiteGrey
                    )
                )
            }
        }
    }
}

@Composable
fun ShimmerSection() {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .width(200.dp)
                .height(200.dp)
                .clip(RoundedCornerShape(8.dp))
                .shimmerEffect()
        )

        Box(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .width(200.dp)
                .height(200.dp)
                .clip(RoundedCornerShape(8.dp))
                .shimmerEffect()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewShimmer() {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        ShimmerSection()
    }
}