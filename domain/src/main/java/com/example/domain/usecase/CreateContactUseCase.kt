package com.example.domain.usecase

import com.example.domain.entities.Contact
import com.example.domain.repo.ContactsRepository

class CreateContactUseCase(private val repo: ContactsRepository) {

    fun invoke(contact: Contact): Long{
        return repo.insert(contact)
    }
}