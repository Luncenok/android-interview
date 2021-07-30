package com.goodylabs.android.interview.ui.characterdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.goodylabs.android.interview.data.models.Character
import com.goodylabs.android.interview.data.repositories.CharacterRepository
import com.goodylabs.android.interview.util.ArgsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
    savedStateHandle: SavedStateHandle
) : ArgsViewModel(savedStateHandle) {

    private val args: CharacterDetailsFragmentArgs by navArgs()

    private val _character = MutableLiveData<Character>()
    val character: LiveData<Character> = _character

    private val _errorText = MutableLiveData<String?>()
    val errorText: LiveData<String?> = _errorText

    fun clearErrorText() {
        _errorText.value = null
    }

    fun refresh() {
        val characterKey = args.characterKey
        viewModelScope.launch {
            try {
                _character.value = characterRepository.getCharacterById(characterKey)
            } catch (e: Exception) {
                _errorText.value = "Error: $e"
            }
        }
    }
}
