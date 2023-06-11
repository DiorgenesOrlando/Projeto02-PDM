package com.example.contacts.views


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.contacts.R
import com.example.contacts.models.Contact
import com.example.contacts.viewmodels.ContactsViewModel

@Composable
fun ContactScreen(
    navController: NavController,
    contactsViewModel: ContactsViewModel
) {

    val uiState by contactsViewModel.contactScreenUiState.collectAsState()

    val contatos = uiState.allContacts
    val status = uiState.status


    if (contatos.isEmpty() && status.equals("erro")) {
        ErrorScreen();
    }else  if(contatos.isEmpty()  && status.equals("carregando")){
        LoadingScreen();
    }

    if (!uiState.onlyFavorites) {



    contactList(

        contacts = uiState.allContacts,


      //  onFavoriteChange = { contact, isFavorite ->
      //      contactsViewModel.onContactIsFavoriteChange(contact, isFavorite)
       // },
        onEditContact = { contactsViewModel.editContact(it, navController) })
    //    onDeleteContact = { contactsViewModel.onDeleteContact(it) }
    //)
}else{
        var contacts: ArrayList<Contact> = ArrayList()
        uiState.allContacts.forEach {
            if (it.isFavorite == true) {
                contacts.add(it)
            }
        }




        contactList(

            contacts = contacts,


            //onFavoriteChange = { contact, isFavorite ->
             //   contactsViewModel.onContactIsFavoriteChange(contact, isFavorite)
            //},
            onEditContact = { contactsViewModel.editContact(it, navController) },
           // onDeleteContact = { contactsViewModel.onDeleteContact(it) }
        )

}

}
@Composable
fun contactList(
    contacts: List<Contact>,
    //onFavoriteChange: (Contact, Boolean) -> Unit,
    onEditContact: (Contact) -> Unit
    //onDeleteContact: (Contact) -> Unit

) {
    LazyColumn(){
        items(contacts) { contact->
            ContactEntry(contact = contact,/* onFavoriteChange = {onFavoriteChange(contact, it)}*/ onEditContact = {onEditContact(contact)}/*, onDeleteContact = {onDeleteContact(contact)}*/)
        }
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()){

        Text(text = stringResource(id = R.string.loading_failed))
    }

}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()){

        Text(text = stringResource(id = R.string.loading))
    }

}

@Composable
fun ContactEntry(
    contact: Contact,
    //onFavoriteChange: (Boolean) -> Unit,
    onEditContact: () -> Unit,
    //onDeleteContact: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { onEditContact() }, elevation = 4.dp,
        backgroundColor = Color(0xFFDFDFDF)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val inicial = contact.name[0].toString()
            Text(text = inicial, fontSize = 28.sp, fontWeight = FontWeight.Bold)

            Text(
                text = contact.name,
                modifier = Modifier.weight(1f)
                    .padding(start = 16.dp)
            )
/*
            IconToggleButton(
                checked = contact.isFavorite,
                onCheckedChange = { onFavoriteChange(!contact.isFavorite) }
            ) {
                if (contact.isFavorite) {
                    Icon(
                        painter = painterResource(id = com.example.contacts.R.drawable.baseline_star_24),
                        contentDescription = "Favorite"
                    )
                } else {
                    Icon(
                        painter = painterResource(id = com.example.contacts.R.drawable.baseline_star_border_24),
                        contentDescription = "Not Favorite"
                    )
                }
            }


            IconToggleButton(
                checked = false,
                onCheckedChange = { onDeleteContact() }
            ) {
                Icon(
                    painter = painterResource(id = com.example.contacts.R.drawable.baseline_delete_forever_24),
                    contentDescription = "Delete Contact"
                )
            }
            */
        }
    }
}
