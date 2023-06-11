package com.example.contacts.views

import androidx.activity.compose.BackHandler
import com.example.contacts.viewmodels.ContactsViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun DetailContactScreen(
    navController: NavController,
    contactsViewModel: ContactsViewModel
){
    BackHandler {
        contactsViewModel.onBackPressed(navController)
        
    }
    val uiState by contactsViewModel.detailScreenUiState.collectAsState()
    InsertEditForm(
        name = uiState.name,
        surname = uiState.surname,
        number = uiState.number,
        email = uiState.email,
        onNameChange = {contactsViewModel.onContactNameChange(it)},
        onSurnameChange = {contactsViewModel.onContactSurnameChange(it)},
        onNumberChange = {contactsViewModel.onContactNumberChange(it)},
        onEmailChange = {contactsViewModel.onContactEmailChange(it)},

    )
}


@Composable
fun InsertEditForm(
    name: String,
    surname: String,
    number: String,
    email: String,
    onNameChange: (String) -> Unit,
    onSurnameChange: (String) -> Unit,
    onNumberChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,

) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Column(horizontalAlignment = Alignment.Start) {
            OutlinedTextField(
                label = { Text(text = "Name") },
                value = name, onValueChange = onNameChange,
                readOnly = true
            )

            Column(horizontalAlignment = Alignment.Start) {
                OutlinedTextField(
                    label = { Text(text = "Surname") },
                    value = surname,onValueChange = onSurnameChange,
                    readOnly = true
                )
            }


            Column(horizontalAlignment = Alignment.Start) {
                    OutlinedTextField(
                        label = { Text(text = "Number") },
                        value = number,
                        onValueChange = onNumberChange,
                        readOnly = true // torna o campo não editável
                    )



                Column(horizontalAlignment = Alignment.Start) {
                        OutlinedTextField(
                            label = { Text(text = "Email") },
                            value = email, onValueChange = onEmailChange,
                            readOnly = true
                        )
                    }
                }
            }
        }






    }
