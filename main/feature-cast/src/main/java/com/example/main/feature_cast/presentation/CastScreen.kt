package com.example.main.feature_cast.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.common_ui.component.TMDBSearchBar
import com.example.common_ui.utils.setImage
import com.example.common_ui.utils.showShimmer
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CastScreen(
    navHostController: NavHostController
) {

    val viewModel: CastViewModel = koinViewModel()
    val query = viewModel.query.collectAsState()
    val isSearch = viewModel.isSearch.collectAsState(initial = false).value
    val searchPagingItems = viewModel.searchCastState.collectAsLazyPagingItems()
    val popularPagingItems = viewModel.popularCastState.collectAsLazyPagingItems()
    val isRefreshing = (searchPagingItems.loadState.refresh is LoadState.Loading && isSearch)
            || (popularPagingItems.loadState.refresh is LoadState.Loading && !isSearch)

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(vertical = 10.dp),
                title = {
                    TMDBSearchBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 16.dp),
                        placeholder = stringResource(id = com.example.common_ui.R.string.search_label),
                        value = query.value,
                        onValueChange = {
                            viewModel.setQuery(it)
                        },
                        keyboardOption = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                viewModel.setQuery(viewModel.query.value)
                            }
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    scrolledContainerColor = Color.Transparent
                )
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(bottom = 100.dp),
            contentAlignment = Alignment.Center
        ) {
            when {
                isRefreshing -> {
                    CircularProgressIndicator()
                }
                else -> {
                    val currentPagingItems = if (isSearch) searchPagingItems else popularPagingItems
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(currentPagingItems.itemCount) { index ->
                            val castItem = currentPagingItems[index]
                            if (castItem != null) {
                                CastItem(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    name = castItem.name ?: "",
                                    thumbnailUrl = castItem.profilePath?.setImage() ?: ""
                                )
                            }
                        }
                        currentPagingItems.apply {
                            when (loadState.append) {
                                is LoadState.Loading -> {
                                    item {
                                        Box(modifier = Modifier
                                            .fillMaxWidth(),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            CircularProgressIndicator()
                                        }
                                    }
                                }
                                is LoadState.Error -> {
                                    item {
                                        Button(onClick = { retry() }) {
                                            Text("Retry")
                                        }
                                    }
                                }
                                else -> Unit
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CastItem(
    modifier: Modifier,
    name: String,
    thumbnailUrl: String,
) {

    var isLoading by remember {
        mutableStateOf(true)
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = thumbnailUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .showShimmer(isLoading),
            onSuccess = { isLoading = false}
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = name,
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.SemiBold,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCastItem() {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        CastItem(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            "Ryan Reynold",
            "dasdas",
        )
    }
}