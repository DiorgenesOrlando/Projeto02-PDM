package com.example.contacts.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.contacts.viewmodels.ContactsViewModel
import com.example.contacts.views.ContactScreen
import com.example.contacts.views.DetailContactScreen
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.example.contacts.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    contactsViewModel: ContactsViewModel = viewModel()

) {

    val navController = rememberNavController()


    val uiState by contactsViewModel.mainScreenUiState.collectAsState()
    val contactState by contactsViewModel.contactScreenUiState.collectAsState()


    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color(0xCB000000)
            ) {
                Text(
                    text = uiState.screenTitle,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }/*,
        floatingActionButton = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(start = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Botao filtrar favoritos
                if(uiState.screenTitle == "CONTATOS"){
                    FloatingActionButton(onClick = {
                        contactState.onlyFavorites = contactState.onlyFavorites == false
                        navController.navigate("contact_list")

                    }) {
                        Icon(
                            painter = painterResource(id = uiState.segIcon),
                            contentDescription = null
                        )
                    }

                }

                Spacer(modifier = Modifier.width(16.dp))
                // bota adicionar

                FloatingActionButton(onClick = {
                    contactsViewModel.navigate(navController)
                }) {
                    Icon(painter = painterResource(id = uiState.fabIcon), contentDescription = null)
                }
            }
        }*/
            ) {
        NavHost(navController = navController, startDestination = "contact_list") {
            composable("contact_list"){
                ContactScreen(navController = navController, contactsViewModel = contactsViewModel)
            }
            composable("detail_contact"){
                DetailContactScreen(navController = navController, contactsViewModel = contactsViewModel)

            }
        }
    }
}


