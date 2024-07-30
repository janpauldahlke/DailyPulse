package com.petros.efthymiou.dailypulse

import kotlinx.coroutines.CoroutineScope

//https://www.baeldung.com/kotlin/open-vs-public-keywords
expect open class BaseViewModel() {
    //structured concurrency
    //all inside can be cancelt, wehn scope is cancelt
    //this is an advantage we will learn more about
    val scope: CoroutineScope
}