package com.petros.efthymiou.dailypulse.android

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.petros.efthymiou.dailypulse.android.Screen.*
import com.petros.efthymiou.dailypulse.android.screens.AboutScreen
import com.petros.efthymiou.dailypulse.android.screens.ArticlesScreen
import com.petros.efthymiou.dailypulse.articles.ArticlesViewModel

@Composable
fun ApplicationScaffold() {

    val navController = rememberNavController()

    Scaffold {
        AppNavHost(
            navController = navController,
            modifier= Modifier
                .fillMaxSize()
                .padding(it),
            )
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier : Modifier = Modifier,
) {
    NavHost(
      navController = navController,
        startDestination = ARTICLES.route,
        modifier = modifier
    ) {
        composable(ARTICLES.route) {
            ArticlesScreen(
                onAboutButtonClick = { navController.navigate(ABOUT_DEVICE.route)},
            )
        }
        composable(ABOUT_DEVICE.route) {
            AboutScreen(
                onAboutButtonClick = { navController.popBackStack()}
            )
        }
    }
}