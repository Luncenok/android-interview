package com.goodylabs.android.interview.ui

enum class MainScreen {
    CharacterList(),
    CharacterDetails();

    companion object {
        fun fromRoute(route: String?): MainScreen =
            when (route?.substringBefore("/")) {
                CharacterList.name -> CharacterList
                CharacterDetails.name -> CharacterDetails
                null -> CharacterList
                else -> throw IllegalArgumentException("Route $route is not recognized.")

            }
    }
}
