package com.goodylabs.android.interview.data.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PageInfo(

    @Json(name = "count")
    val count: Int?,

    @Json(name = "pages")
    val pages: Int?,

    @Json(name = "next")
    val next: String?,

    @Json(name = "prev")
    val prev: String?
)