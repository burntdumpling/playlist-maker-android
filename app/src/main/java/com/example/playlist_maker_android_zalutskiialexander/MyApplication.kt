package com.example.playlist_maker_android_zalutskiialexander

import android.app.Application
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import com.example.playlist_maker_android_zalutskiialexander.data.di.repositoryModule
import com.example.playlist_maker_android_zalutskiialexander.ui.view_model.SearchViewModel

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            val viewModelModule = module {
                viewModel {
                    SearchViewModel(get())
                }
            }
            modules(repositoryModule, viewModelModule)
        }
    }
}