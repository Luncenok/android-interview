package com.goodylabs.android.interview.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Character(

    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "status") val status: String,
    @Json(name = "species") val species: String,
    @Json(name = "type") val type: String,
    @Json(name = "gender") val gender: String,
    @Json(name = "origin") val origin: Origin,
    @Json(name = "location") val location: Location,
    @Json(name = "image") val image: String,
    @Json(name = "episode") val episode: List<String>,
    @Json(name = "url") val url: String,
    @Json(name = "created") val created: String
)
