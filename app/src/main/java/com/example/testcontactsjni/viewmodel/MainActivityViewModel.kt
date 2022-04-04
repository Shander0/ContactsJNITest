package com.example.testcontactsjni.viewmodel

import android.content.Context
import android.util.Log
import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.validate.ContactPhoneValidator
import com.example.domain.entities.Contact
import com.example.domain.usecase.CreateContactUseCase
import com.example.domain.usecase.DeleteContactUseCase
import com.example.domain.usecase.GetContactsUseCase
import com.example.testcontactsjni.R
import com.example.testcontactsjni.app.App

class MainActivityViewModel(private val contactPhoneValidator: ContactPhoneValidator,
                            private val createContactUseCase: CreateContactUseCase,
                            private val getContactsUseCase: GetContactsUseCase,
                            private val deleteContactUseCase: DeleteContactUseCase,
                            private val context: Context
): ViewModel() {

    var columnCount = 1
    val dialogTitle = ObservableField("")
    val dialogTextFirst = ObservableField("")
    val dialogTextSecond = ObservableField("")
    val isErrorDialog = ObservableBoolean(true)
    val currentContact = ObservableField(Contact()).apply {
        addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                dialogTextFirst.set(this@apply.get()!!.name)
                dialogTextSecond.set(this@apply.get()!!.phone)
            }

        })
    }
    val enteringName = ObservableField("")
    val enteringPhone = ObservableField("")

    val dialogDismiss = MutableLiveData(false)
    val errorExists = MutableLiveData(false)
    val insertSuccess = MutableLiveData(false)

    fun subscribeToContacts(): LiveData<List<Contact>> {
        return getContactsUseCase.invoke()
    }

    fun onContactAddAttempt() {
        when {
            enteringName.get()!!.isBlank() -> {
                isErrorDialog.set(true)
                errorExists.value = true
                dialogTitle.set(context.getString(R.string.enter_error_title))
                dialogTextFirst.set(context.getString(R.string.enter_name_err))
            }
            enteringPhone.get()!!.isBlank() -> {
                isErrorDialog.set(true)
                errorExists.value = true
                dialogTitle.set(context.getString(R.string.enter_error_title))
                dialogTextFirst.set(context.getString(R.string.enter_phone_err))
            }
            !contactPhoneValidator.validatePhone(enteringPhone.get()!!) -> {
                isErrorDialog.set(true)
                errorExists.value = true
                dialogTitle.set(context.getString(R.string.enter_error_title))
                dialogTextFirst.set(context.getString(R.string.incorrect_format_err))
                dialogTextSecond.set(context.getString(R.string.incorrect_phone_hint))
            }
            createContactUseCase.invoke(Contact(enteringPhone.get()!!, enteringName.get()!!)) == -1L -> {
                isErrorDialog.set(true)
                errorExists.value = true
                dialogTitle.set(context.getString(R.string.enter_error_title))
                dialogTextFirst.set(context.getString(R.string.phone_exists_err))
            }
            else -> {
                insertSuccess.value = true
            }
        }
    }

    fun onDialogOk() {
        dialogDismiss.value = true
        if (!isErrorDialog.get()) {
            deleteContactUseCase.invoke(currentContact.get()!!)
        } else {
            errorExists.value = false
        }
        resetDialog()
    }

    fun onDialogCancel() {
        dialogDismiss.value = true
        resetDialog()
    }

    private fun resetDialog() {
        dialogTitle.set("")
        dialogTextFirst.set("")
        dialogTextSecond.set("")
        isErrorDialog.set(false)
    }
}