package com.petros.efthymiou.dailypulse.articles

import com.petros.efthymiou.dailypulse.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticlesViewModel: BaseViewModel() {
    // in corortines streams are called flows
    // compare to observable
    // inner mutable private, exposed public not mutable
    // kotlin flow vs kotlin state flow
    private val _articlesState : MutableStateFlow<ArticlesState> = MutableStateFlow(ArticlesState())
    val articlesState: StateFlow<ArticlesState> get() = _articlesState

    //interesting like a lifecycle hook in angular
    init {
        getArticles()
    }

    private fun getArticles() {
        scope.launch {
            //imitate network
            delay(200)
            _articlesState.emit(ArticlesState())
        }
    }
}