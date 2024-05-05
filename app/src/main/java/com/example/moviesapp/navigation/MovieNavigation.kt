package com.example.moviesapp.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviesapp.model.Boss
import com.example.moviesapp.screens.details.DetailsScreen
import com.example.moviesapp.screens.home.HomeScreen
import com.example.moviesapp.util.fromJson
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@SuppressLint("NewApi")
@Composable
fun MovieNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = MoviesScreens.HomeScreen.name) {

        composable(MoviesScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }

        composable(MoviesScreens.DetailsScreen.name + "/{boss}",
            arguments = listOf(navArgument("boss") {
                type = NavType.StringType
            })
        ) {

            it.arguments?.getString("boss")?.let { jsonString ->
                val deJsonSting = URLDecoder.decode(jsonString, StandardCharsets.UTF_8.toString())
                val boss = deJsonSting.fromJson(Boss::class.java)
                DetailsScreen(navController = navController, boss)
            }
        }
    }
}
