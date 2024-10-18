package com.uvg.melodymaster.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.uvg.melodymaster.data.local.dao.ArtistDao
import com.uvg.melodymaster.data.local.dao.SongDao
import com.uvg.melodymaster.data.local.entity.ArtistEntity
import com.uvg.melodymaster.data.local.entity.SongEntity

@Database(
    entities = [
        ArtistEntity::class,
        SongEntity::class
    ],
    version = 1
)
abstract class MelodyDb: RoomDatabase() {
    abstract fun artistDao(): ArtistDao
    abstract fun SongDao(): SongDao
}