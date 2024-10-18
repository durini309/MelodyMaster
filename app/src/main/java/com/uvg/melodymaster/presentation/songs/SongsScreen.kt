package com.uvg.melodymaster.presentation.songs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uvg.melodymaster.domain.model.Song
import com.uvg.melodymaster.presentation.ui.common.LoadingLayout
import com.uvg.melodymaster.presentation.ui.theme.MelodyMasterTheme
import com.uvg.melodymaster.presentation.utilities.randomColor

@Composable
fun SongsRoute() {

}

@Composable
private fun SongsScreen(
    state: SongsScreenState,
    onFavClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    if (state.isLoading) {
        Box(modifier) {
            LoadingLayout(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else {
        LazyColumn(modifier = modifier) {
            items(state.songs) { song ->
                SongItem(
                    song = song,
                    onFavClick = onFavClick
                )
            }
        }
    }
}

@Composable
private fun SongItem(
    song: Song,
    onFavClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(
                horizontal = 8.dp,
                vertical = 8.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color = song.color)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = song.name,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = song.artistName,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = song.genre,
                style = MaterialTheme.typography.labelMedium
            )
        }
        IconButton(onClick = {
            onFavClick(song.id)
        }) {
            if (song.isFavorite) {
                Icon(
                    Icons.Outlined.Favorite,
                    contentDescription = "Favorito",
                    tint = MaterialTheme.colorScheme.error
                )
            } else {
                Icon(
                    Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorito"
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewSongsScreen() {
    MelodyMasterTheme {
        Surface {
            SongsScreen(
                state = SongsScreenState(
                    isLoading = false,
                    songs = listOf(
                        Song(
                            id = 1,
                            name = "Himno de GT",
                            artistName = "Rafael Álvarez Ovalle ft Juan Carlos Durini",
                            color = randomColor(),
                            genre = "Himno",
                            isFavorite = false
                        ),
                        Song(
                            id = 1,
                            name = "Himno de GT",
                            artistName = "Rafael ",
                            color = randomColor(),
                            genre = "Himno",
                            isFavorite = true
                        ),
                        Song(
                            id = 1,
                            name = "Himno de GT",
                            artistName = "Rafael Álvarez Ovalle",
                            color = randomColor(),
                            genre = "Himno",
                            isFavorite = false
                        )
                    ),
                ),
                onFavClick = {},
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}