package com.goodylabs.android.interview.ui.characterlist

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.goodylabs.android.interview.data.models.Character

data class CharacterListUiState(
    val characterDetails: List<Character>? = null,
    val isLoading: Boolean = false,
    val throwError: Boolean = false
)

@Composable
fun CharacterListBody(
    onCharacterClick: (Int) -> Unit,
    viewModel: CharacterListViewModel = viewModel()
) {

    val listItems = viewModel.listCharacters.observeAsState()
    val errorText = viewModel.errorText.observeAsState()

    val uiState = when {
        listItems.value.isNullOrEmpty() -> CharacterListUiState(isLoading = true)
        !errorText.value.isNullOrBlank() -> CharacterListUiState(throwError = true)
        else -> CharacterListUiState(characterDetails = listItems.value)
    }

    when {
        uiState.characterDetails != null -> {
            CharacterListContent(
                onCharacterClick = onCharacterClick,
                viewModel = viewModel,
                listItems = uiState.characterDetails
            )
        }
        uiState.isLoading -> {
            LazyColumn {
                items(20) {
                    CharacterListLoadingItem()
                }
            }
        }
        else -> {
            Text(
                text = "An error occurred. Please restart app to try again",
                modifier = Modifier.padding(30.dp),
                style = MaterialTheme.typography.h3
            )
        }
    }
}

@Composable
fun CharacterListContent(
    onCharacterClick: (Int) -> Unit,
    viewModel: CharacterListViewModel,
    listItems: List<Character>
) {
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
            .padding(10.dp)
            .fillMaxWidth()
            .height(75.dp)
            .clickable(onClick = { onCharacterClick(characterId) }),
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

@Composable
fun CharacterListLoadingItem() {

    val infiniteTransition = rememberInfiniteTransition()
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 1000
                0.7f at 500
            },
            repeatMode = RepeatMode.Reverse
        )
    )
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(75.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(75.dp)
                .clip(CircleShape)
                .background(Color.LightGray.copy(alpha = alpha))
        )
        Spacer(Modifier.width(10.dp))
        Box(
            modifier = Modifier
                .background(Color.LightGray.copy(alpha = alpha))
                .width(150.dp)
                .height(16.dp)
        )
    }
}
