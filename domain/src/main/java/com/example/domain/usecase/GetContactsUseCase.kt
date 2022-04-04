package com.example.domain.usecase

import androidx.lifecycle.LiveData
import com.example.domain.entities.Contact
import com.example.domain.repo.ContactsRepository

class GetContactsUseCase(private val repo: ContactsRepository) {

    fun invoke(): LiveData<List<Contact>> {
        return repo.getAll()
    }
}