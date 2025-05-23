package com.example.newsaggregator.data.repository

import com.example.newsaggregator.data.rss.RssFeed
import com.example.newsaggregator.data.rss.dto.ItemDto
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val feed: RssFeed
) {
    suspend fun fetchNews(): List<ItemDto> {
        return feed.getRss().channel.items
    }
}
