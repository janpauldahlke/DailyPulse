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
    private val _articlesState : MutableStateFlow<ArticlesState> = MutableStateFlow(ArticlesState
        (
        loading = true,
        articles = listOf(),
        error = null
        )
    )
    val articlesState: StateFlow<ArticlesState> get() = _articlesState

    //interesting like a lifecycle hook in angular
    init {
        getArticles()
    }

    private fun getArticles() {
        scope.launch {
            // suspend functions can only be called inside coroutines

            delay(1000)
            _articlesState.emit(ArticlesState(
                loading = false,
                error = "oops an error!"
            ))

            delay(1000)
            val fetchedArticles = fetchArticles()

            _articlesState.emit(ArticlesState(
                articles = fetchedArticles,
                loading = false,
                error = null
            ))
        }
    }

    suspend fun fetchArticles(): List<Article> = mockArticles


    //mocks for now
    private val mockArticles = listOf(
        Article(
            "foobarack bars foo to farobaro",
            "Turkey buffalo cupim pork chop, burgdoggen bresaola capicola jerky short ribs flank beef pastrami kielbasa spare ribs salami. Tongue ham hock meatball, pork loin sirloin pork cow sausage venison pastrami chislic swine corned beef. Sausage boudin cupim fatback. Short ribs andouille t-bone, ham hock pork belly ball tip brisket pancetta leberkas porchetta bacon cow. T-bone fatback sirloin bacon bresaola brisket. Prosciutto chicken pork belly ham corned beef, shoulder beef ribs ground round pancetta frankfurter bresaola. Landjaeger tongue pork chop brisket beef shoulder, t-bone picanha corned beef chicken ribeye boudin.",
            "30.07.2024",
            "https://i.imgur.com/IjDqX8J.jpeg"
        ),
        Article(
            "balanda maka huma",
            "Bacon ipsum dolor amet ribeye strip steak drumstick tail prosciutto sirloin tongue beef filet mignon cupim tenderloin. Frankfurter t-bone leberkas, ham swine picanha tongue chicken kevin pork belly kielbasa ham hock pork chop. Swine kevin cow filet mignon jowl chuck tail shankle sirloin tri-tip, ribeye shank buffalo biltong pork belly. Pork loin sirloin venison jowl. Drumstick corned beef doner porchetta sirloin leberkas capicola ribeye meatball jerky kevin filet mignon pork chop prosciutto. Burgdoggen sausage swine jerky pastrami pancetta.",
            "01.07.2024",
            "https://i.imgur.com/XPDNoTX.jpeg"
        ),
        Article(
            "Heisenberg invents new Cult for  Cthulhu",
            "Short ribs picanha landjaeger pork loin, spare ribs meatball burgdoggen. Pork landjaeger buffalo cupim bresaola spare ribs pork loin biltong tail hamburger. Frankfurter pig meatloaf bacon pork chop biltong flank landjaeger, buffalo ground round turkey short ribs turducken. Pork loin hamburger pig, kevin tongue t-bone biltong bacon meatloaf doner corned beef pork belly chicken shoulder. Chuck turducken swine filet mignon, bacon biltong fatback pork loin.",
            "11.07.2024",
            "https://i.imgur.com/3NI3Tod.jpeg"
        ),
        Article(
            "Bacon is tasty ad",
            "Does your lorem ipsum text long for something a little meatier? Give our generator a try… it’s tasty!",
            "21.07.2024",
            "https://i.imgur.com/52BwECT.jpeg"
        )
    )
}