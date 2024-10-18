package com.uvg.melodymaster.presentation.artists

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uvg.melodymaster.domain.model.Artist
import com.uvg.melodymaster.domain.model.Song
import com.uvg.melodymaster.presentation.ui.common.LoadingLayout
import com.uvg.melodymaster.presentation.ui.theme.MelodyMasterTheme
import com.uvg.melodymaster.presentation.utilities.randomColor

@Composable
fun ArtistsRoute() {

}

@Composable
private fun ArtistsScreen(
    state: ArtistsScreenState,
    onFavClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    if (state.isLoading) {
        Box(modifier = modifier) {
            LoadingLayout(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else {
        LazyColumn(modifier) {
            items(state.artists) { artist ->
                ArtistItem(
                    artist = artist,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun ArtistItem(
    artist: Artist,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = artist.name,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = artist.monthlyListeners.toString(),
            )
        }
        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(artist.songs) { song ->
                ArtistSongItem(
                    song = song,
                    modifier = Modifier.width(160.dp)
                )
            }
        }
    }
}

@Composable
fun ArtistSongItem(
    song: Song,
    modifier: Modifier = Modifier,
) {
    OutlinedCard(
        modifier = modifier
    ) {
        Column {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = song.color)
            )
            Row {
                Text(text = song.name)
                Text(text = song.genre)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewArtistsScreen() {
    MelodyMasterTheme {
        Surface {
            ArtistsScreen(
                state = ArtistsScreenState(
                    isLoading = false,
                    artists = listOf(
                        Artist(
                            name = "Juan Carlos Durini Serrano",
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
                            monthlyListeners = 4_000_000
                        )
                    )
                ),
                onFavClick = {}
            )
        }
    }
}