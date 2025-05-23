package com.example.newsaggregator.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsaggregator.data.NewsUiState
import com.example.newsaggregator.data.repository.NewsRepository
import com.example.newsaggregator.data.rss.dto.ItemDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _news = MutableStateFlow<List<ItemDto>>(emptyList())
    val news: StateFlow<List<ItemDto>> = _news

    private val _uiState = MutableStateFlow(NewsUiState())
    val uiState: StateFlow<NewsUiState> = _uiState

    init {
        loadNews()
    }

    private fun loadNews() {
        viewModelScope.launch {
            try {
                val news = repository.fetchNews()
                _uiState.value = NewsUiState(news = news)
            } catch (e: Exception) {
                _uiState.value = NewsUiState(error = "Ошибка загрузки новостей")
            }
        }
    }

}
