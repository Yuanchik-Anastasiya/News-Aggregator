package com.example.newsaggregator.ui.news

import androidx.compose.runtime.Composable
import com.example.newsaggregator.data.rss.dto.ItemDto
import com.example.newsaggregator.viewmodel.NewsViewModel
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun NewsListRoute(
    viewModel: NewsViewModel = hiltViewModel(),
    onItemClick: (ItemDto) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    NewsListScreen(
        newsItems = uiState.news,
        onItemClick = onItemClick
    )
}
