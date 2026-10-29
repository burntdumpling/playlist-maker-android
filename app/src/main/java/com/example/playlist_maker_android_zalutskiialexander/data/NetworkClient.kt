package com.example.playlist_maker_android_zalutskiialexander.data

import com.example.playlist_maker_android_zalutskiialexander.data.dto.BaseResponse

interface NetworkClient {
    fun doRequest(dto: Any): BaseResponse
}
