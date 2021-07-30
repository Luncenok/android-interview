package com.goodylabs.android.interview.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.goodylabs.android.interview.ui.characterdetails.CharacterDetailsBody
import com.goodylabs.android.interview.ui.characterlist.CharacterListBody
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
        composable(MainScreen.CharacterList.name) {
            CharacterListBody()
        }
        composable(MainScreen.CharacterDetails.name) {
            CharacterDetailsBody()
        }
    }
}
