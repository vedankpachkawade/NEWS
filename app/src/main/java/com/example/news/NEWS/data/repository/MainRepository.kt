package com.example.news.NEWS.data.repository

import com.example.news.NEWS.data.model.NewsResponse
import com.example.news.NEWS.data.server.NewsApiService
import com.example.news.NEWS.utils.TOKEN
import com.example.news.NEWS.utils.di.MyResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository @Inject constructor(private val api : NewsApiService)
{
    suspend fun lastNews() : Flow<MyResponse<NewsResponse>> = flow{
        emit(MyResponse.loading())
        val response = api.getTopHeadLines(TOKEN, "in")
        if(response.isSuccessful)
            emit(MyResponse.success(response.body()))
        else emit(MyResponse.error("Please try again later!"))

    }.catch {
        emit(MyResponse.error(it.message.toString()))
    }
}