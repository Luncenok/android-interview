package com.goodylabs.android.interview.data.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharactersContainer(

    @Json(name = "info")
    val info: PageInfo?,

    //TODO Change Any to correct serializable character object based on API documentation
    @Json(name = "results")
    val results: List<Any>?
)