package com.example.simplelist.repository


import com.example.Constants
import com.example.simplelist.remote.NewsApi
import com.example.simplelist.remote.response.Doc
import com.example.simplelist.remote.response.Response

import kotlinx.coroutines.flow.flow

class NewsRepository(private val api: NewsApi) {

     fun getNewList(newsType: String) = flow<List<Doc?>>{
        val response: Response = api.getNewsList(newsType, Constants.API_KEY).response
        val listOfDocs = response.docs
        emit(listOfDocs)
    }

}



