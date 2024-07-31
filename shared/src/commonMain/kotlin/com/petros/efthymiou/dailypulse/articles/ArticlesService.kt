package com.petros.efthymiou.dailypulse.articles

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticlesService(
    private val httpClient: HttpClient
) {
    private val country = "us";
    private val newsCategory = "sport"
    //not worries, this is totally safe, isnt it?
    private val apiKey = "4d7e13f5123b4d41beab6a2a3fd74a8d"
    // combined fields
    private val API_URL = "https://newsapi.org/v2/top-headlines?country=$country&category=$newsCategory&apiKey=$apiKey"

    suspend fun fetchArticles() : List<ArticleRaw>{
        val response: ArticlesResponse = httpClient.get(API_URL).body()
        println("---> fetched ${response.articles}")
        return response.articles
    }
}