package com.example.playlist_maker_android_zalutskiialexander.data.di

import com.example.playlist_maker_android_zalutskiialexander.data.NetworkClient
import com.example.playlist_maker_android_zalutskiialexander.data.network.RetrofitNetworkClient
import com.example.playlist_maker_android_zalutskiialexander.data.network.TracksRepositoryImpl
import com.example.playlist_maker_android_zalutskiialexander.domain.api.TracksRepository
import org.koin.dsl.module


val repositoryModule = module { // создаёт модуль Koin, в котором регистрируются все зависимости для работы с данными
    factory<NetworkClient> { // регистрирует класс RetrofitNetworkClient как реализацию интерфейса NetworkClient
        RetrofitNetworkClient()
    }

    factory<TracksRepository> { // регистрирует TracksRepositoryImpl как реализацию интерфейса TracksRepository.
        TracksRepositoryImpl(get())
    }
}