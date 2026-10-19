package com.example.playlist_maker_android_zalutskiialexander

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.core.view.WindowCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.playlist_maker_android_zalutskiialexander.ui.theme.PlaylistmakerandroidZalutskiiAlexanderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlaylistmakerandroidZalutskiiAlexanderTheme(
                darkTheme = false,
                dynamicColor = false
            ) {
                val navController = rememberNavController()
                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = backStackEntry?.destination?.route
                    ?: PlaylistScreen.MAIN.name

                SideEffect {
                    WindowCompat.getInsetsController(window, window.decorView)
                        .isAppearanceLightStatusBars =
                        currentRoute != PlaylistScreen.MAIN.name
                }

                val playlists = stringResource(R.string.playlists)
                val favorites = stringResource(R.string.favorites)
                val buttonClicked = stringResource(R.string.button_clicked)
                PlaylistHost(
                    navController = navController,
                    
                    onPlaylistsClick = { showButtonToast(buttonClicked.format(playlists)) },
                    onFavoritesClick = { showButtonToast(buttonClicked.format(favorites)) }
                )
            }
        }
    }

    private fun showButtonToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
