package com.example.main.feature_home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.common_ui.theme.BlueAccent
import com.example.common_ui.theme.DarkGrey
import com.example.main.feature_home.data.ImageData
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController
) {

    val imageList = listOf(
        ImageData(
            "https://image.tmdb.org/t/p/original/gklrevVndG98GHGDwfm8y8kxESo.jpg"
        ),
        ImageData(
            "https://image.tmdb.org/t/p/original/gklrevVndG98GHGDwfm8y8kxESo.jpg"
        ),
        ImageData(
            "https://image.tmdb.org/t/p/original/gklrevVndG98GHGDwfm8y8kxESo.jpg"
        ),
        ImageData(
            "https://image.tmdb.org/t/p/original/gklrevVndG98GHGDwfm8y8kxESo.jpg"
        ),
        ImageData(
            "https://image.tmdb.org/t/p/original/gklrevVndG98GHGDwfm8y8kxESo.jpg"
        ),
        ImageData(
            "https://image.tmdb.org/t/p/original/gklrevVndG98GHGDwfm8y8kxESo.jpg"
        )
    )

    val hazeState = remember { HazeState() }
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
        ) {

            TrendingSliderSection(hazeState, imageList)
        }
    }
}

@Composable
fun TrendingSliderSection(
    hazeState: HazeState,
    imageList: List<ImageData>
) {
    val pagerState = rememberPagerState {
        imageList.size
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
                model = imageList[page].imageUrl,
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
                    text = "Trending",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineMedium
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "tgl",
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