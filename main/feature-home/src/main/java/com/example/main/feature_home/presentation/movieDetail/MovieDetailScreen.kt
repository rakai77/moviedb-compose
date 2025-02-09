package com.example.main.feature_home.presentation.movieDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.common_ui.theme.WhiteGrey
import com.example.common_ui.utils.dateConvert
import com.example.common_ui.utils.setImage
import com.example.core.domain.model.MovieDetail
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild
import dev.chrisbanes.haze.materials.ExperimentalHazeMaterialsApi
import dev.chrisbanes.haze.materials.HazeMaterials
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieDetailScreen(
    movieId: String,
    onBackClick: () -> Unit
) {

    val hazeState = remember { HazeState() }
    val viewModel: MovieDetailViewModel = koinViewModel()
    val movieDetailState by viewModel.movieDetailState.collectAsState(MovieDetailState.Idle)

    LaunchedEffect(movieId) {
        viewModel.getMovieDetail(movieId)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        when(val state = movieDetailState) {
            is MovieDetailState.LoadingMovieDetail -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is MovieDetailState.SuccessMovieDetail -> {
                HeaderDetailSection(
                    modifier = Modifier.fillMaxWidth(),
                    hazeState = hazeState,
                    movieDetail = state.movieDetail,
                    onBackClick = onBackClick
                )
            }
            else -> Unit
        }
    }
}


@OptIn(ExperimentalHazeMaterialsApi::class)
@Composable
fun HeaderDetailSection(
    modifier: Modifier,
    hazeState: HazeState,
    movieDetail: MovieDetail,
    onBackClick: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .haze(hazeState),
            contentScale = ContentScale.Crop,
            model = movieDetail.backdropPath?.setImage() ?: "",
            contentDescription = null
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = 0f,
                        endY = 600f
                    )
                )
        )
        IconButton(
            modifier = Modifier
                .padding(top = 56.dp, start = 16.dp)
                .hazeChild(
                    hazeState,
                    style = HazeMaterials.thin(),
                    shape = RoundedCornerShape(4.dp)
                )
                .size(36.dp)
                .align(Alignment.TopStart),
            onClick = { onBackClick() }
        ) {
            Icon(
                modifier = Modifier,
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                tint = WhiteGrey,
                contentDescription = null
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomStart),
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = movieDetail.title ?: "",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = WhiteGrey,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            HeaderContent(
                modifier = Modifier.fillMaxWidth(),
                movieDetail = movieDetail
            )
        }
    }
}

@Composable
fun HeaderContent(
    modifier: Modifier,
    movieDetail: MovieDetail
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(id = com.example.common_ui.R.drawable.ic_calendar),
            contentDescription = null,
            tint = WhiteGrey
        )
        Text(
            modifier = Modifier.padding(horizontal = 4.dp),
            text = movieDetail.releaseDate?.dateConvert() ?: "",
            style = MaterialTheme.typography.bodySmall.copy(
                color = WhiteGrey,
                fontStyle = FontStyle.Italic
            ),
        )
        VerticalDivider(
            modifier = Modifier
                .height(18.dp)
                .padding(horizontal = 8.dp),
            thickness = 1.dp,
            color = WhiteGrey
        )
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(id = com.example.common_ui.R.drawable.ic_clock),
            contentDescription = null,
            tint = WhiteGrey
        )
        Text(
            modifier = Modifier.padding(horizontal = 4.dp),
            text = "${movieDetail.runtime} minutes",
            style = MaterialTheme.typography.bodySmall.copy(
                color = WhiteGrey
            ),
        )
        VerticalDivider(
            modifier = Modifier
                .height(18.dp)
                .padding(horizontal = 8.dp),
            thickness = 1.dp,
            color = WhiteGrey
        )
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(id = com.example.common_ui.R.drawable.ic_film),
            contentDescription = null,
            tint = WhiteGrey
        )
        Text(
            modifier = Modifier.padding(horizontal = 4.dp),
            text = movieDetail.genres.first().name,
            style = MaterialTheme.typography.bodySmall.copy(
                color = WhiteGrey
            ),
        )
    }
}



