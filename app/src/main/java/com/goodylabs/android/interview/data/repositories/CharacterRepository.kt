package com.goodylabs.android.interview.data.repositories

import com.goodylabs.android.interview.data.api.CharacterService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepository @Inject constructor(private val characterService: CharacterService) {

    suspend fun getCharacterContainer() = characterService.getCharacterContainer()

    suspend fun getCharacterNextContainer(page: Int) =
        characterService.getCharacterNextContainer(page)

    suspend fun getCharacterById(characterId: Int) = characterService.getCharacter(characterId)
}