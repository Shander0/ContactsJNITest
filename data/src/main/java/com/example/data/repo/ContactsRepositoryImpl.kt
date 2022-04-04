package com.example.data.repo

import androidx.lifecycle.LiveData
import com.example.domain.dao.ContactsDao
import com.example.domain.entities.Contact
import com.example.domain.repo.ContactsRepository

class ContactsRepositoryImpl(private val dao: ContactsDao): ContactsRepository {

    override fun getAll(): LiveData<List<Contact>> {
        return dao.getAll()
    }

    override fun insert(contact: Contact): Long {
        return dao.add(contact)
    }

    override fun remove(contact: Contact) {
        dao.remove(contact)
    }


}