package com.example.contacts.network

import com.example.contacts.models.Contact
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL = "http://192.168.0.102:8080"
val json = Json { ignoreUnknownKeys = true }

private val  retrofit  = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL).build()

interface ContactApiService{
    @GET("/contacts")
    suspend fun getContacts() : List<Contact>
}

object ContactApi {
    val retrofitService : ContactApiService by lazy {
        retrofit.create(ContactApiService::class.java)
    }
}