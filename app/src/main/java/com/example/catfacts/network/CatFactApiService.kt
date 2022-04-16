package com.example.catfacts.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://cat-fact.herokuapp.com"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CatFactApiService {
    @GET("facts/random")
    suspend fun getFact(): CatFact
}

object CatFactApi {
    val retrofitService: CatFactApiService by lazy {
        retrofit.create(CatFactApiService::class.java)
    }
}