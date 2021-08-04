package com.goodylabs.android.interview.ui.characterlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodylabs.android.interview.data.models.Character
import com.goodylabs.android.interview.data.models.CharactersContainer
import com.goodylabs.android.interview.data.repositories.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(private val characterRepository: CharacterRepository) :
    ViewModel() {

    private val _listCharacters = MutableLiveData<List<Character>?>()
    val listCharacters: LiveData<List<Character>?> = _listCharacters

    private val _errorText = MutableLiveData<String?>()
    val errorText: LiveData<String?> = _errorText

    private var nextPage: Int = 2
    private var nextPageTest: Int = 2
    private lateinit var currentContainer: CharactersContainer

    init {
        viewModelScope.launch {
            try {
                val container = characterRepository.getCharacterContainer()
                _listCharacters.value = container.results
                currentContainer = container
            } catch (e: Exception) {
                _errorText.value = "Error: $e"
            }
        }
    }

    fun clearErrorText() {
        _errorText.value = null
    }

    fun loadNextList() {
        viewModelScope.launch {
            try {
                if (nextPage <= currentContainer.info.pages && nextPage == nextPageTest) {
                    nextPageTest++
                    val charactersContainer =
                        characterRepository.getCharacterNextContainer(nextPage)
                    charactersContainer.results?.forEach {
                        _listCharacters.value = _listCharacters.value?.plus(it)
                    }
                    nextPage++
                }
            } catch (e: Exception) {
                nextPageTest--
                _errorText.value = "Error: $e"
            }
        }
    }
}
