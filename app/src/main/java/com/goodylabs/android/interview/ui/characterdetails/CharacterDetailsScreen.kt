package com.goodylabs.android.interview.ui.characterdetails

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.goodylabs.android.interview.R
import com.goodylabs.android.interview.data.models.Character
import com.goodylabs.android.interview.data.models.Location
import com.goodylabs.android.interview.data.models.Origin

data class CharacterDetailsUiState(
    val characterDetails: Character? = null,
    val isLoading: Boolean = false,
    val throwError: Boolean = false
)

@Composable
fun CharacterDetailsBody(
    characterId: Int,
    viewModel: CharacterDetailsViewModel = viewModel()
) {

    val uiState by produceState(initialValue = CharacterDetailsUiState(isLoading = true)) {
        value = try {
            val character = viewModel.getCharacter(characterId)
            CharacterDetailsUiState(characterDetails = character)
        } catch (e: Exception) {
            CharacterDetailsUiState(throwError = true)
        }
    }

    when {
        uiState.characterDetails != null -> {
            CharacterDetailsContent(character = uiState.characterDetails!!)
        }
        uiState.isLoading -> {
            CharacterDetailsLoading()
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
fun CharacterDetailsContent(character: Character) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = rememberImagePainter(
                data = character.image
            ),
            contentDescription = "Character Avatar",
            modifier = Modifier
                .padding(top = 50.dp)
                .size(200.dp)
        )
        val textModifier = Modifier
            .padding(16.dp)
            .align(Alignment.CenterHorizontally)
        Text(
            text = character.name,
            modifier = textModifier,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h2
        )
        Text(
            text = stringResource(R.string.character_species_format, character.species),
            modifier = textModifier,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h3
        )
        Text(
            text = stringResource(R.string.character_gender_format, character.gender),
            modifier = textModifier,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h3
        )
        Text(
            text = stringResource(R.string.character_status_format, character.status),
            modifier = textModifier,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h3
        )
    }
}

@Composable
fun CharacterDetailsLoading() {
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
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .padding(top = 50.dp)
                .size(200.dp)
                .clip(CircleShape)
                .background(Color.LightGray.copy(alpha = alpha))
        )
        val textModifier = Modifier
            .padding(16.dp)
            .align(Alignment.CenterHorizontally)
            .background(Color.LightGray.copy(alpha = alpha))
        Box(
            modifier = textModifier
                .width(250.dp)
                .height(60.dp)
        )
        Box(
            modifier = textModifier
                .width(210.dp)
                .height(48.dp)
        )
        Box(
            modifier = textModifier
                .width(150.dp)
                .height(48.dp)
        )
        Box(
            modifier = textModifier
                .width(230.dp)
                .height(48.dp)
        )
    }
}

@Preview
@Composable
fun CharacterDetailsContentPreview() {
    CharacterDetailsContent(
        Character(
            1,
            "Rick Sanchez",
            "Alive",
            "Human",
            "",
            "Male",
            Origin("", ""),
            Location("", ""),
            "",
            listOf(""),
            "",
            ""
        )
    )
}
