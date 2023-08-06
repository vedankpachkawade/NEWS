package com.example.news.NEWS.data.server

import com.example.news.NEWS.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headLines")
    suspend fun getTopHeadLines(
    @Query("apiKey") apiKey : String,
    @Query("country") country : String

    ) : Response<NewsResponse>


}