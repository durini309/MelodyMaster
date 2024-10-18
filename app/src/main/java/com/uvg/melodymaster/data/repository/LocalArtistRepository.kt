package com.uvg.melodymaster.data.repository

import com.uvg.melodymaster.data.local.MusicApi
import com.uvg.melodymaster.data.local.dao.ArtistDao
import com.uvg.melodymaster.data.local.dao.SongDao
import com.uvg.melodymaster.data.local.entity.mapToEntity
import com.uvg.melodymaster.data.local.entity.mapToModel
import com.uvg.melodymaster.domain.model.Artist

class LocalArtistRepository(
    private val artistDao: ArtistDao,
    private val songDao: SongDao
) {
    suspend fun getArtists(): List<Artist> {
        val localArtists = artistDao.getAllArtists()

        return localArtists.map { localArtist ->
            val localSongs = songDao.getSongsFromArtist(localArtist.id)
            localArtist.mapToModel(localSongs)
        }
    }

    suspend fun populateLocalArtistDatabase() {
        val remoteArtists = MusicApi.getArtists()
        val localArtists = remoteArtists.map { remoteArtist ->
            remoteArtist.mapToEntity()
        }
        artistDao.insertAll(localArtists)
    }
}