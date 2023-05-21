package com.example.contacts.viewmodels

import com.example.contacts.models.Contact

data class ContactScreenUiState(
    var onlyFavorites : Boolean = false,

    val allContacts: List<Contact> = listOf(
        Contact("Diórgenes", "Fagundes", "(47)99999-9999", "diorgenes@gmail.com", isFavorite = false),
        Contact("Thales", "Paulo", "(47)99624-0146", "thalespaulo17@gmail.com", isFavorite = true),
    )
)