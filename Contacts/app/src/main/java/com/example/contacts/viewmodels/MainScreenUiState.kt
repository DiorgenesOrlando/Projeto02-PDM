package com.example.contacts.viewmodels

import android.graphics.drawable.ColorDrawable
import androidx.annotation.DrawableRes
import com.example.contacts.R

data class MainScreenUiState(
    val screenTitle: String = "CONTATOS",
    var editContact : Boolean= false,

    @DrawableRes val fabIcon: Int = R.drawable.baseline_add_24,
    @DrawableRes val segIcon:Int  = R.drawable.baseline_star_24,


    )
