package com.petros.efthymiou.dailypulse.android

import android.app.Application
import com.petros.efthymiou.dailypulse.android.di.viewModelsModule
import com.petros.efthymiou.dailypulse.di.sharedKoinMoudules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


//this is the andriod application Class, like app in angular
// this is singleton, stay alive until SIGKILL
class DailyPulseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        val modules = sharedKoinMoudules +  viewModelsModule

        startKoin {
            androidContext(this@DailyPulseApplication)
            modules(modules)
        }
    }
}