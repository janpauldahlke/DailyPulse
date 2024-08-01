package com.petros.efthymiou.dailypulse.articles.di

import com.petros.efthymiou.dailypulse.articles.ArticlesService
import com.petros.efthymiou.dailypulse.articles.ArticlesUseCase
import com.petros.efthymiou.dailypulse.articles.ArticlesViewModel
import org.koin.dsl.module

// this will be the "injector" for the whole articles feature
// it will contain all instantiations of all the dependencies

val articlesModules = module {
    single<ArticlesService> {
        ArticlesService(get())
    }

    single<ArticlesUseCase> {
        ArticlesUseCase(get())
    }
    //we only work in IOS since android needs the android viewModel
    single<ArticlesViewModel> {
        ArticlesViewModel(get())
    }
}