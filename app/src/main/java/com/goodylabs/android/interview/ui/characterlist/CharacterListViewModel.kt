package com.goodylabs.android.interview.ui.characterlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodylabs.android.interview.data.models.Character
import com.goodylabs.android.interview.data.models.CharactersContainer
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

    private var nextPage: Int = 2
    private lateinit var currentContainer: CharactersContainer

    init {
        viewModelScope.launch {
            val container = characterRepository.getCharacterContainer()
            _listCharacters.value = container.results
            currentContainer = container
        }
    }

    fun loadNextList() {
        viewModelScope.launch {
            if (nextPage <= currentContainer.info.pages) {
                val charactersContainer = characterRepository.getCharacterNextContainer(nextPage)
                charactersContainer.results?.forEach {
                    _listCharacters.value = _listCharacters.value?.plus(it)
                }
                nextPage++
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}