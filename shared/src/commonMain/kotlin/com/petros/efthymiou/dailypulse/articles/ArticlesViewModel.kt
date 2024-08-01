package com.petros.efthymiou.dailypulse.articles

import com.petros.efthymiou.dailypulse.BaseViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class ArticlesViewModel(
    private val useCase: ArticlesUseCase
): BaseViewModel() {
    // in corortines streams are called flows
    // compare to observable
    // inner mutable private, exposed public not mutable
    // kotlin flow vs kotlin state flow
    private val _articlesState : MutableStateFlow<ArticlesState> = MutableStateFlow(ArticlesState
        (
        loading = true,
        articles = listOf(),
        error = null
        )
    )
    val articlesState: StateFlow<ArticlesState> get() = _articlesState
    suspend fun fetchArticles(): List<Article> = useCase.getArticles()

    //interesting like a lifecycle hook in angular
    init {
        getArticles()
    }

    private fun getArticles() {
        scope.launch {
            val fetchedArticles = fetchArticles()
              _articlesState.emit(ArticlesState(
                articles = fetchedArticles,
                loading = false,
                error = null
            ))
        }
    }

}