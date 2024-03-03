package com.example.simplelist.remote

import com.example.simplelist.remote.response.NewsApp
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("svc/search/v2/articlesearch.json/")
    suspend fun getNewsList(@Query("q") type: String,@Query("api-key") apiKey:String): NewsApp
}