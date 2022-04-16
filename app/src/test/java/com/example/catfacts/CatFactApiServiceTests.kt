package com.example.catfacts

import BaseTest
import com.example.catfacts.network.CatFactApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class CatFactApiServiceTests: BaseTest() {
    private lateinit var service: CatFactApiService

    @Before
    fun setup() {
        val url = mockWebServer.url("https://cat-fact.herokuapp.com")
        service = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            ))
            .build()
            .create(CatFactApiService::class.java)
    }

    @Test
    fun api_service() {
        runBlocking {
            val apiResponse = service.getFact()

            assertNotNull(apiResponse)
        }
    }
}