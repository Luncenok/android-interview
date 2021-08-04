package com.goodylabs.android.interview.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.goodylabs.android.interview.ui.characterdetails.CharacterDetailsBody
import com.goodylabs.android.interview.ui.characterdetails.CharacterDetailsViewModel
import com.goodylabs.android.interview.ui.characterlist.CharacterListBody
import com.goodylabs.android.interview.ui.characterlist.CharacterListViewModel
import com.goodylabs.android.interview.ui.theme.AndroidinterviewTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InterviewApp()
        }
    }
}

@Composable
fun InterviewApp() {
    AndroidinterviewTheme {
        val navController = rememberNavController()

        Scaffold { innerPadding ->
            InterviewNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun InterviewNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = MainScreen.CharacterList.name,
        modifier = modifier
    ) {
        val characterListName = MainScreen.CharacterList.name
        val characterDetailsName = MainScreen.CharacterDetails.name
        composable(
            route = characterListName
        ) {
            val model: CharacterListViewModel = hiltViewModel(it)
            CharacterListBody(onCharacterClick = { id ->
                navController.navigate("$characterDetailsName/$id")
            }, model)
        }
        composable(
            route = "$characterDetailsName/{id}",
            arguments = listOf(navArgument(name = "id") { type = NavType.IntType })
        ) { entry ->
            val model: CharacterDetailsViewModel = hiltViewModel(entry)
            val characterId = entry.arguments?.getInt("id")
            CharacterDetailsBody(characterId ?: 0, model)
        }
    }
}
