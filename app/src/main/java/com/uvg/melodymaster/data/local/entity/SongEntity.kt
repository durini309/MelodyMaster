package com.uvg.melodymaster.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uvg.melodymaster.data.local.SongDTO
import com.uvg.melodymaster.domain.model.Song
import com.uvg.melodymaster.presentation.utilities.randomColor

@Entity
data class SongEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val isFavorite: Boolean,
    val artistId: String,
    val artistName: String,
    val genre: String
)

fun SongEntity.mapToModel(): Song {
    return Song(
        id = id,
        name = name,
        isFavorite = isFavorite,
        artistName = artistName,
        genre = genre,
        color = randomColor()
    )
}

fun SongDTO.mapToEntity(artistName: String): SongEntity {
    return SongEntity(
        id = id,
        name = name,
        artistId = artist_id,
        genre = genre,
        isFavorite = false,
        artistName = artistName
    )
}