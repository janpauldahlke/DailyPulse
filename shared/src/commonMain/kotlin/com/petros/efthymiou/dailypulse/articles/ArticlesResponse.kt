package com.petros.efthymiou.dailypulse.articles

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticlesResponse(
    //serialName = hoew the filed is named in the backend api we are fetching from
    @SerialName("status")
    val status: String,
    @SerialName("totalResults")
    val totalResults: Int,
    @SerialName("articles")
    val articles : List<ArticleRaw>
)

@Serializable
data class ArticleRaw(
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String?,
    @SerialName("url")
    val url: String,
    @SerialName("urlToImage")
    val imageURL: String?,
    @SerialName("publishedAt")
    val publishedAt: String
)

