package com.example.domain.repo

import androidx.lifecycle.LiveData
import com.example.domain.entities.Contact

interface ContactsRepository {

    fun getAll(): LiveData<List<Contact>>
    fun insert(contact: Contact): Long
    fun remove(contact: Contact)
}