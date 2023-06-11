package com.example.contacts.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.contacts.R
import com.example.contacts.models.Contact
import com.example.contacts.network.ContactApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException

class ContactsViewModel: ViewModel() {

    private var _mainScreenUiState: MutableStateFlow<MainScreenUiState> = MutableStateFlow(
        MainScreenUiState()
    )
    val mainScreenUiState: StateFlow<MainScreenUiState> = _mainScreenUiState.asStateFlow()

    private var _contactScreenUiState: MutableStateFlow<ContactScreenUiState> = MutableStateFlow(
        ContactScreenUiState()
    )
    public  val contactScreenUiState: StateFlow<ContactScreenUiState> = _contactScreenUiState.asStateFlow()

    private var _detailContactScreenUiState: MutableStateFlow<DetailScreenUiState> =
        MutableStateFlow(
            DetailScreenUiState()
        )
    val detailScreenUiState: StateFlow<DetailScreenUiState> =
        _detailContactScreenUiState.asStateFlow()

   // var editContact: Boolean = false
    var contactToEdit: Contact = Contact()


    init {
        getContacts()
    }

    fun onContactNameChange(newContactName: String) {
        _detailContactScreenUiState.update { currentState ->
            currentState.copy(name = newContactName)
        }
    }

    fun onContactSurnameChange(newContactSurname: String) {
        _detailContactScreenUiState.update { currentState ->
            currentState.copy(surname = newContactSurname)
        }
    }

    fun onContactNumberChange(newContactNumber: String) {
        _detailContactScreenUiState.update { currentState ->
            currentState.copy(number = newContactNumber)
        }
    }

    fun onContactEmailChange(newContactEmail: String) {
        _detailContactScreenUiState.update { currentState ->
            currentState.copy(email = newContactEmail)
        }
    }

    fun onDeleteContact(contactToDelete: Contact) {
        val allContactsTemp = _contactScreenUiState.value.allContacts.toMutableList()
        var pos = -1
        allContactsTemp.forEachIndexed { index, contact ->
            if (contactToDelete == contact) {
                pos = index
            }
        }
        allContactsTemp.removeAt(pos)
        _contactScreenUiState.update { currentState ->
            currentState.copy(allContacts = allContactsTemp.toList())
        }
    }


    fun onContactIsFavoriteChange(updatedContact: Contact, newContactFavorite: Boolean) {
        val allContactsTemp = _contactScreenUiState.value.allContacts.toMutableList()
        var pos = -1
        allContactsTemp.forEachIndexed { index, contact ->
            if (updatedContact == contact) {
                pos = index
            }
        }
        allContactsTemp.removeAt(pos)
        allContactsTemp.add(pos, updatedContact.copy(isFavorite = newContactFavorite))
        _contactScreenUiState.update { currentState ->
            currentState.copy(allContacts = allContactsTemp.toList())
        }
    }

    fun navigate(navController: NavController) {
        if (_mainScreenUiState.value.screenTitle == "CONTATOS") {
            if (_mainScreenUiState.value.editContact) {
                _mainScreenUiState.update { currentState ->
                    currentState.copy(
                        screenTitle = "DETALHES",
                        fabIcon = R.drawable.baseline_check_24,
                       // segIcon = -1//ColorDrawable(Color.TRANSPARENT)
                    )
                }
            }
            navController.navigate("detail_contact")
        } else {
            _mainScreenUiState.update { currentState ->
                currentState.copy(
                    screenTitle = "CONTATOS",
                    fabIcon = R.drawable.baseline_add_24,
                )
            }


            if (_mainScreenUiState.value.editContact) {
                val allContactsTemp = _contactScreenUiState.value.allContacts.toMutableList()
                var pos = -1
                allContactsTemp.forEachIndexed { index, contact ->
                    if (contactToEdit == contact) {
                        pos = index
                    }
                }
                allContactsTemp.removeAt(pos)
                allContactsTemp.add(
                    pos, contactToEdit.copy(
                        name = _detailContactScreenUiState.value.name,
                        surname = _detailContactScreenUiState.value.surname,
                        number = _detailContactScreenUiState.value.number,
                        email = _detailContactScreenUiState.value.email,
                    )
                )
                _contactScreenUiState.update { currentState ->
                    currentState.copy(allContacts = allContactsTemp.toList())

                }
            } else {
                _contactScreenUiState.update { currentState ->
                    currentState.copy(
                        allContacts = currentState.allContacts + Contact(
                            name = _detailContactScreenUiState.value.name,
                            surname = _detailContactScreenUiState.value.surname,
                            number = _detailContactScreenUiState.value.number,
                            email = _detailContactScreenUiState.value.email,
                        )
                    )

                }
            }
            _detailContactScreenUiState.update {
                DetailScreenUiState()
            }
            contactToEdit = Contact()
            navController.navigate("contact_list") {
                popUpTo("contact_list") {
                    inclusive = true
                }
            }
        }
    }

    fun editContact(contact: Contact, navController: NavController) {
        _mainScreenUiState.value.editContact = true
    contactToEdit = contact
        _detailContactScreenUiState.update { currentState ->
        currentState.copy(
            name = contact.name,
            surname = contact.surname,
            number = contact.number,
            email = contact.email
        )
    }
    navigate(navController)

    }

    fun clearForm(){
        _detailContactScreenUiState.update {
                currentState->
            currentState.copy(
                name = "",
                surname = "",
                number  = "",
                email = "",
                isFavorite = false
            )
        }
    }
    fun onBackPressed(navController: NavController){
        clearForm()
        _mainScreenUiState.update { currentState->
            currentState.copy(
                screenTitle = "CONTATOS",
                fabIcon = R.drawable.baseline_add_24,
                editContact = false

            )

        }


        navController.navigate("contact_list")

    }
    fun getContacts() {
        viewModelScope.launch{
            try{

                _contactScreenUiState.update {currentState->
                    currentState.copy(
                        status = "carregando"
                    )
                }
                val listResult =  ContactApi.retrofitService.getContacts()
                _contactScreenUiState.update { currentState ->
                    currentState.copy(
                        allContacts = listResult,
                        status = "ok"

                    )

                }

            }catch(e : IOException){
                _contactScreenUiState.update {currentState->
                    currentState.copy(
                        status = "erro"
                    )
                }

            }

        }
    }

}