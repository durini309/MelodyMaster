package com.uvg.melodymaster.data.repository

import com.uvg.melodymaster.data.local.MusicApi
import com.uvg.melodymaster.data.local.dao.SongDao
import com.uvg.melodymaster.data.local.entity.mapToEntity
import com.uvg.melodymaster.data.local.entity.mapToModel
import com.uvg.melodymaster.domain.model.Song
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalSongRepository(
    private val songDao: SongDao
) {
    fun getAllSongs(): Flow<List<Song>> =
        songDao.getAllSongs().map { localSongs ->
            localSongs.map { localSong ->
                localSong.mapToModel()
            }
        }

    suspend fun setFavorite(songId: Int, isFavorite: Boolean) {
        songDao.updateFavorite(
            id = songId,
            isFav = isFavorite
        )
    }

    suspend fun populateLocalSongDatabase() {
        val remoteSongs = MusicApi.getSongs()
        val remoteArtists = MusicApi.getArtists()
        val songEntities = remoteSongs.map { remoteSong ->
            val artistName = remoteArtists.find { artist ->
                artist.id == remoteSong.artist_id
            }
            remoteSong.mapToEntity(artistName?.name ?: "Desconocido")
        }

        songDao.insertAll(songEntities)
    }
}