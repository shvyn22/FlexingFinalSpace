package shvyn22.flexingfinalspace.presentation.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import shvyn22.flexingfinalspace.data.local.model.CharacterModel
import shvyn22.flexingfinalspace.data.local.model.EpisodeModel
import shvyn22.flexingfinalspace.data.local.model.QuoteModel

@Composable
fun LazyProgressList(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    lazyContent: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 10.dp, bottom = 0.dp, start = 10.dp, end = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) CircularProgressIndicator()
        lazyContent()
    }
}

@ExperimentalFoundationApi
@Composable
fun CharacterGridProgressList(
    isLoading: Boolean,
    characters: List<CharacterModel>,
    onClick: (CharacterModel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyProgressList(
        isLoading = isLoading,
        modifier = modifier
    ) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(3)
        ) {
            items(characters) {
                GridItem(
                    name = it.name,
                    imgUrl = it.imgURL,
                    modifier = Modifier.clickable { onClick(it) }
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun EpisodeGridProgressList(
    isLoading: Boolean,
    episodes: List<EpisodeModel>,
    onClick: (EpisodeModel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyProgressList(
        isLoading = isLoading,
        modifier = modifier
    ) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(3),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(episodes) {
                GridItem(
                    name = it.name,
                    imgUrl = it.imgURL,
                    modifier = Modifier.clickable { onClick(it) }
                )
            }
        }
    }
}

@Composable
fun QuoteColumnProgressList(
    isLoading: Boolean,
    quotes: List<QuoteModel>,
    modifier: Modifier = Modifier
) {
    LazyProgressList(
        isLoading = isLoading,
        modifier = modifier
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(quotes) {
                QuoteItem(quote = it)
            }
        }
    }
}