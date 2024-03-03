package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplelist.Util.toSimpleDateFormat
import com.example.simplelist.model.NewsData
import com.example.simplelist.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    private var _newsState = MutableStateFlow<List<NewsData?>>(emptyList())
    val newsState: StateFlow<List<NewsData?>>
        get() = _newsState

    init {
        viewModelScope.launch {
            newsRepository.getNewList("election")
                .flowOn(Dispatchers.IO)
                .map { docs->
                    val newsDataList = mutableListOf<NewsData>()

                    docs.forEach { doc ->
                        val multimediaUrls = doc?.multimedia?.get(0)?.url
                        val title = doc?.headline?.main
                        val description = doc?.abstract
                        val date = doc?.pub_date?.toSimpleDateFormat()

                        newsDataList.add(NewsData(multimediaUrls,title,description,date))
                    }
                    newsDataList
                }
                .collect {
                _newsState.value = it
            }
        }
    }

}
