package com.example.playlist_maker_android_zalutskiialexander.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.playlist_maker_android_zalutskiialexander.ui.navigation.PlaylistHost
import com.example.playlist_maker_android_zalutskiialexander.ui.theme.PlaylistmakerandroidZalutskiiAlexanderTheme as PlaylistmakerTheme
import com.example.playlist_maker_android_zalutskiialexander.ui.view_model.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    private val searchViewModel: SearchViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlaylistmakerTheme {
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                    PlaylistHost(
                        modifier = Modifier.Companion.padding(innerPadding),
                        navController = rememberNavController(),
                        gson = remember { Gson() },
                        startDestination = "main",
                        searchViewModel = searchViewModel
                    )
                }
            }
        }
    }
}
