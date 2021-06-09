package com.goodylabs.android.interview.utilities

import com.goodylabs.android.interview.data.models.Character
import com.goodylabs.android.interview.data.models.Location
import com.goodylabs.android.interview.data.models.Origin

val testCharacters = listOf(
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
    ),
    Character(
        2,
        "Morty Smith",
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

val testCharacter = testCharacters[0]
