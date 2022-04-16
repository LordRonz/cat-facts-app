package com.example.catfacts.network

import com.squareup.moshi.Json

data class CatFact(
    @Json(name = "_id") val id: String,
    val text: String,
)