package com.goodylabs.android.interview.ui.characterdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.goodylabs.android.interview.data.models.Character
import com.goodylabs.android.interview.data.repositories.CharacterRepository
import com.goodylabs.android.interview.util.ArgsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val savedStateHandle: SavedStateHandle
) : ArgsViewModel(savedStateHandle) {
    private val viewModelJob = Job()

    private val args: CharacterDetailsFragmentArgs by navArgs()

    private val _character = MutableLiveData<Character>()
    val character: LiveData<Character> = _character

    fun refresh() {
        val characterKey = args.characterKey
        viewModelScope.launch {
            _character.value = characterRepository.getCharacterById(characterKey)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}