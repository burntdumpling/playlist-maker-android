package com.example.playlist_maker_android_zalutskiialexander

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun PlaylistHost(
    navController: NavHostController,
    onPlaylistsClick: () -> Unit,
    onFavoritesClick: () -> Unit
) {
    val openSearch: () -> Unit = {
        navController.navigate(PlaylistScreen.SEARCH.name) { launchSingleTop = true }
    }
    val openSettings: () -> Unit = {
        navController.navigate(PlaylistScreen.SETTINGS.name) { launchSingleTop = true }
    }
    val goBack: () -> Unit = { navController.popBackStack() }

    NavHost(
        navController = navController,
        startDestination = PlaylistScreen.MAIN.name
    ) {
        composable(PlaylistScreen.MAIN.name) {
            MainScreen(
                onSearchClick = openSearch,
                onPlaylistsClick = onPlaylistsClick,
                onFavoritesClick = onFavoritesClick,
                onSettingsClick = openSettings
            )
        }
        composable(PlaylistScreen.SEARCH.name) {
            SearchScreen(onBackClick = goBack)
        }
        composable(PlaylistScreen.SETTINGS.name) {
            SettingsScreen(onBackClick = goBack)
        }
    }
}
