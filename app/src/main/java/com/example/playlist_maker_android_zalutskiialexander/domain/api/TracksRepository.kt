package com.example.playlist_maker_android_zalutskiialexander.domain.api

import com.example.playlist_maker_android_zalutskiialexander.domain.model.Track

interface TracksRepository {
    fun searchTracks(expression: String): List<Track>
    suspend fun searchTracksContains(expression: String): List<Track>
}
