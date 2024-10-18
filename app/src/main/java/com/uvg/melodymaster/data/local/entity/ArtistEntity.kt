package com.uvg.melodymaster.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uvg.melodymaster.data.local.ArtistDTO
import com.uvg.melodymaster.domain.model.Artist

@Entity
data class ArtistEntity(
    @PrimaryKey val id: String,
    val name: String,
    val monthlyListeners: Int
)

fun ArtistEntity.mapToModel(localSongs: List<SongEntity>): Artist {
    return Artist(
        name = name,
        songs = localSongs.map { localSong -> localSong.mapToModel() },
        monthlyListeners = monthlyListeners
    )
}

fun ArtistDTO.mapToEntity(): ArtistEntity {
    return ArtistEntity(
        id = id,
        name = name,
        monthlyListeners = monthlyListeners
    )
}
