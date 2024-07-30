package com.petros.efthymiou.dailypulse.articles

data class ArticlesState(
    val article: List<Article> = listOf(),
    val loading: Boolean = false,
    val error: String? = null
)