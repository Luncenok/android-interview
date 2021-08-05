package com.goodylabs.android.interview.ui.characterdetails

import androidx.lifecycle.ViewModel
import com.goodylabs.android.interview.data.repositories.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    suspend fun getCharacter(characterId: Int) = characterRepository.getCharacterById(characterId)

}
