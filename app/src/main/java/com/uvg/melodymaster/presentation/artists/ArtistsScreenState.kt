package com.uvg.melodymaster.presentation.artists

import com.uvg.melodymaster.domain.model.Artist

data class ArtistsScreenState(
    val isLoading: Boolean = true,
    val artists: List<Artist> = emptyList()
)
