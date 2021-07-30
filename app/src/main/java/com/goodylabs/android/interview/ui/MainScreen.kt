package com.goodylabs.android.interview.ui

enum class MainScreen {
    ListScreen(),
    DetailsScreen();

    companion object {
        fun fromRoute(route: String?): MainScreen =
            when (route?.substringBefore("/")) {
                ListScreen.name -> ListScreen
                DetailsScreen.name -> DetailsScreen
                null -> ListScreen
                else -> throw IllegalArgumentException("Route $route is not recognized.")

            }
    }
}
