package com.example.catfacts.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://api.thecatapi.com"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CatImageApiService {
    @GET("v1/images/search")
    suspend fun getImage(): List<CatImage>
}

object CatImageApi {
    val retrofitService: CatImageApiService by lazy {
        retrofit.create(CatImageApiService::class.java)
    }
}