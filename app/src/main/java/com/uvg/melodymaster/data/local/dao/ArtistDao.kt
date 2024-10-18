package com.uvg.melodymaster.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.uvg.melodymaster.data.local.entity.ArtistEntity

@Dao
interface ArtistDao {
    @Query("SELECT * FROM ArtistEntity")
    suspend fun getAllArtists() : List<ArtistEntity>

    @Insert
    suspend fun insertAll(artists: List<ArtistEntity>)
}