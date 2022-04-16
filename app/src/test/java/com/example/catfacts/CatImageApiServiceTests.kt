package com.example.catfacts

import BaseTest
import com.example.catfacts.network.CatImageApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class CatImageApiServiceTests: BaseTest() {
    private lateinit var service: CatImageApiService

    @Before
    fun setup() {
        val url = mockWebServer.url("https://api.thecatapi.com")
        service = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            ))
            .build()
            .create(CatImageApiService::class.java)
    }

    @Test
    fun api_service() {
        runBlocking {
            val apiResponse = service.getImage()

            assertNotNull(apiResponse)
            assertTrue("The list was empty", apiResponse.isNotEmpty())
        }
    }
}