package com.petros.efthymiou.dailypulse.android.di

import com.petros.efthymiou.dailypulse.articles.ArticlesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    // this is very specfic in koin
    viewModel { ArticlesViewModel(get()) }
}