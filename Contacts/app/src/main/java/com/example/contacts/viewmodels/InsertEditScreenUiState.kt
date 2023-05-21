package com.example.contacts.viewmodels

data class InsertEditScreenUiState(
    val name: String = "",
    val surname : String = "",
    val number: String = "",
    val email : String = "",
    val isFavorite : Boolean = false,
)

