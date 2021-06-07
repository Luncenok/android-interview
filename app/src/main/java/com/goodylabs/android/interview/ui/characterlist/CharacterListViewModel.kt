package com.goodylabs.android.interview.ui.characterlist

import androidx.lifecycle.ViewModel
import com.goodylabs.android.interview.data.repositories.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(private val characterRepository: CharacterRepository) :
    ViewModel() {
}