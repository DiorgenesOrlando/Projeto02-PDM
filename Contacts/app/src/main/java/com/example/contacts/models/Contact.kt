package com.example.contacts.models

data class Contact(
    val name: String = "",
    val surname: String = "",
    val number: String = "",
    val email: String = "",
    val isFavorite: Boolean = false
)


