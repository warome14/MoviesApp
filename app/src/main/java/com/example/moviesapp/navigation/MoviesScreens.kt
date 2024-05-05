package com.example.moviesapp.navigation

enum class MoviesScreens {
    HomeScreen,
    DetailsScreen;

    companion object {
        fun fromRoute(route: String?): MoviesScreens {
            return when (route?.substringBefore("/")) {
                HomeScreen.name -> HomeScreen
                HomeScreen.name -> DetailsScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
        }
    }
}