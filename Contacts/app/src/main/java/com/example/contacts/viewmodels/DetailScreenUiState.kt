package com.example.contacts.viewmodels

data class DetailScreenUiState(
    val name: String = "",
    val surname : String = "",
    val number: String = "",
    val email : String = "",
    val isFavorite : Boolean = false,
)

