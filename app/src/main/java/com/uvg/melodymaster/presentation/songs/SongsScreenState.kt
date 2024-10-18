package com.uvg.melodymaster.presentation.songs

import com.uvg.melodymaster.domain.model.Song

data class SongsScreenState(
    val isLoading: Boolean = true,
    val songs: List<Song> = emptyList(),
)
