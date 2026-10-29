package com.example.playlist_maker_android_zalutskiialexander.data.network

import com.example.playlist_maker_android_zalutskiialexander.data.NetworkClient
import com.example.playlist_maker_android_zalutskiialexander.data.dto.TrackDto
import com.example.playlist_maker_android_zalutskiialexander.data.dto.TracksSearchRequest
import com.example.playlist_maker_android_zalutskiialexander.data.dto.TracksSearchResponse
import com.example.playlist_maker_android_zalutskiialexander.domain.api.TracksRepository
import com.example.playlist_maker_android_zalutskiialexander.domain.model.Track
import kotlinx.coroutines.delay

class TracksRepositoryImpl(private val networkClient: NetworkClient) : TracksRepository {
    val tracksDto = listOf<TrackDto>(
        TrackDto(1, "Yesterday (Remastered 2009)", "The Beatles", 125000L),
        TrackDto(2, "Here Comes The Sun (Remastered...)", "The Beatles", 187000L),
        TrackDto(3, "No Reply", "The Beatles", 152000L),
        TrackDto(4, "Let It Be", "The Beatles", 243000L),
        TrackDto(5, "Girl", "The Beatles", 138000L),
        TrackDto(6, "Michelle", "The Beatles", 167000L),
        TrackDto(7, "Eleanor Rigby", "The Beatles", 198000L),
        TrackDto(8, "Come Together", "The Beatles", 259000L)
    )

    override suspend fun searchTracksContains(expression: String): List<Track> {
        // Имитация обращения по сети. Задержка 3 секунды
        delay(3000)

        // Фильтруем локальный список tracksDto по вхождению expression
        return tracksDto
            .filter { track ->
                track.trackName.contains(expression, ignoreCase = true) ||
                    track.artistName.contains(expression, ignoreCase = true)
            }
            .map { trackDto ->
                val seconds = trackDto.trackTimeMillis / 1000
                val minutes = seconds / 60
                val trackTime = "%02d:%02d".format(minutes, seconds % 60)
                Track(trackDto.id, trackDto.trackName, trackDto.artistName, trackTime)
            }
    }

    override fun searchTracks(expression: String): List<Track> {
        val response = networkClient.doRequest(TracksSearchRequest(expression))
        if (response.resultCode == 200) {
            return (response as TracksSearchResponse).results.map {
                val seconds = it.trackTimeMillis / 1000
                val minutes = seconds / 60
                val trackTime = "%02d:%02d".format(minutes, seconds % 60)
                Track(it.id, it.trackName, it.artistName, trackTime)
            }
        } else {
            return emptyList()
        }
    }
}
