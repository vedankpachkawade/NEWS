package com.example.news.NEWS.data.model

import android.accounts.AuthenticatorDescription
import com.google.gson.annotations.SerializedName

data class NewsResponse (
    val articles : List<Article>?=null

    )
data class Article(
    var title: String,
    var description: String,
    var url: String,
    @SerializedName("urlToImage")
    var imageUrl: String,


)