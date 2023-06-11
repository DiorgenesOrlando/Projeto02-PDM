package com.example.contacts.models

import kotlinx.serialization.Serializable

@Serializable
data class Contact(
    val id : Long = 0,
    val name: String = "",
    val surname: String = "",
    val number: String = "",
    val email: String = "",
    val isFavorite: Boolean = false
)


