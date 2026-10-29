package com.example.playlist_maker_android_zalutskiialexander.data.network

import com.example.playlist_maker_android_zalutskiialexander.data.NetworkClient
import com.example.playlist_maker_android_zalutskiialexander.data.dto.BaseResponse
import com.example.playlist_maker_android_zalutskiialexander.data.dto.TracksSearchResponse

// Учебная заглушка из спринта: реальных сетевых запросов пока нет.
class RetrofitNetworkClient : NetworkClient {
    override fun doRequest(dto: Any): BaseResponse {
        return TracksSearchResponse(listOf())
    }
}
