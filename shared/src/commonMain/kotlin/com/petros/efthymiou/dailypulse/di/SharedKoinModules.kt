package com.petros.efthymiou.dailypulse.di

import com.petros.efthymiou.dailypulse.articles.di.articlesModules

val sharedKoinMoudules = listOf(
    articlesModules,
    networkModule
)