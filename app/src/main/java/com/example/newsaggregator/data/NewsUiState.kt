package com.example.newsaggregator.data

import com.example.newsaggregator.data.rss.dto.ItemDto

data class NewsUiState(
    val news: List<ItemDto> = emptyList(),
    val error: String? = null
)

