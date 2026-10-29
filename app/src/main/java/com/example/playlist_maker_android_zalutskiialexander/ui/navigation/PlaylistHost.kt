package com.example.playlist_maker_android_zalutskiialexander.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.gson.Gson
import com.example.playlist_maker_android_zalutskiialexander.domain.model.Track
import com.example.playlist_maker_android_zalutskiialexander.ui.main.MainScreen
import com.example.playlist_maker_android_zalutskiialexander.ui.search.DetailsScreen
import com.example.playlist_maker_android_zalutskiialexander.ui.search.SearchScreen
import com.example.playlist_maker_android_zalutskiialexander.ui.setting.SettingsScreen
import com.example.playlist_maker_android_zalutskiialexander.ui.view_model.SearchViewModel


@Composable
fun PlaylistHost(
    modifier: Modifier = Modifier,
    startDestination: String = "main",
    navController: NavHostController,
    gson: Gson,
    searchViewModel: SearchViewModel
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = "main") {
            MainScreen(
                navigateToSearch = { navController.navigate("search") },
                navigateToSettings = { navController.navigate("setting") }
            )
        }


        composable(route = "search") {
            SearchScreen(
                searchViewModel = searchViewModel,
                navigateToDetailScreen = { track ->
                    val trackJson = gson.toJson(track)
                    navController.navigate("details/$trackJson")
                },
                navigateBack = { navController.popBackStack() }
            )
        }
        composable(
            route = "details/{trackJson}"
        ) { backStackEntry ->
            val trackJson = backStackEntry.arguments?.getString("trackJson")
            if (trackJson != null) {
                val track = gson.fromJson(trackJson, Track::class.java)
                DetailsScreen(track = track)
            }
        }
        composable(route = "setting") {
            SettingsScreen(
                modifier = Modifier,
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}
