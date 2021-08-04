package com.goodylabs.android.interview.ui.characterdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.goodylabs.android.interview.R

@Composable
fun CharacterDetailsBody(
    characterId: Int,
    viewModel: CharacterDetailsViewModel = viewModel()
) {
    viewModel.refresh(characterId)
    val character = viewModel.character.observeAsState()
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = rememberImagePainter(
                data = character.value?.image
            ),
            contentDescription = "Character Avatar",
            modifier = Modifier
                .size(200.dp)
                .padding(top = 50.dp)
        )
        val textModifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(16.dp)
        Text(
            text = character.value?.name ?: "",
            modifier = textModifier,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h2
        )
        Text(
            text = stringResource(
                R.string.character_species_format,
                character.value?.species ?: ""
            ),
            modifier = textModifier,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h3
        )
        Text(
            text = stringResource(R.string.character_gender_format, character.value?.gender ?: ""),
            modifier = textModifier,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h3
        )
        Text(
            text = stringResource(R.string.character_status_format, character.value?.status ?: ""),
            modifier = textModifier,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h3
        )
    }
}
