package com.goodylabs.android.interview.ui.characterlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation

@Composable
fun CharacterListBody(
    onCharacterClick: (Int) -> Unit,
    viewModel: CharacterListViewModel = viewModel()
) {
    val listItems = viewModel.listCharacters.observeAsState().value ?: emptyList()
    val scrollState = rememberLazyListState()

    LazyColumn(state = scrollState) {

        val allItemsMinusVisible = (listItems.size - 1) - scrollState.firstVisibleItemIndex
        val visibleItemsSize = scrollState.layoutInfo.visibleItemsInfo.size - 1
        if (allItemsMinusVisible == visibleItemsSize && allItemsMinusVisible != -1 && visibleItemsSize != -1) {
            viewModel.loadNextList()
        }
        items(listItems) {
            CharacterListItem(
                avatarUrl = it.image,
                characterName = it.name,
                characterId = it.id,
                onCharacterClick = onCharacterClick
            )
        }
    }
}

@Composable
fun CharacterListItem(
    modifier: Modifier = Modifier,
    avatarUrl: String,
    characterName: String,
    characterId: Int,
    onCharacterClick: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(75.dp)
            .clickable(onClick = { onCharacterClick(characterId) })
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(
                data = avatarUrl,
                builder = { transformations(CircleCropTransformation()) }),
            contentDescription = "Character Avatar",
            modifier = Modifier.size(75.dp)
        )
        Spacer(Modifier.width(10.dp))
        Text(
            modifier = Modifier.padding(horizontal = 10.dp),
            style = MaterialTheme.typography.body1,
            text = characterName
        )
    }
}
