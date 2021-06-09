package com.goodylabs.android.interview.ui.characterlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodylabs.android.interview.data.models.Character
import com.goodylabs.android.interview.data.repositories.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(private val characterRepository: CharacterRepository) :
    ViewModel() {
    private val viewModelJob = Job()

    private val _listCharacters = MutableLiveData<List<Character>?>()
    val listCharacters: LiveData<List<Character>?> = _listCharacters

    init {
        viewModelScope.launch {
            _listCharacters.value = characterRepository.getCharacterContainer().results
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}