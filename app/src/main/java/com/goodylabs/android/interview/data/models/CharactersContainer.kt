package com.goodylabs.android.interview.data.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharactersContainer(

    @Json(name = "info")
    val info: PageInfo,

    @Json(name = "results")
    val results: List<Character>?
)