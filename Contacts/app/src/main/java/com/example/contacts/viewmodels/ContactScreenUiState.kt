package com.example.contacts.viewmodels

import android.util.Log
import com.example.contacts.models.Contact
import com.example.contacts.network.ContactApiService
import androidx.lifecycle.viewModelScope
import com.example.contacts.network.ContactApi

import java.io.IOException

data class ContactScreenUiState(
    var onlyFavorites : Boolean = false,

    var status : String = "carregando",

    var allContacts: List<Contact> = listOf(

      //  Contact("Diórgenes", "Fagundes", "(47)99999-9999", "diorgenes@gmail.com", isFavorite = false),
       // Contact("Thales", "Paulo", "(47)99624-0146", "thalespaulo17@gmail.com", isFavorite = true),
    )
)