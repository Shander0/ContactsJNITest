package com.example.domain.usecase

import com.example.domain.entities.Contact
import com.example.domain.repo.ContactsRepository

class DeleteContactUseCase(private val repo: ContactsRepository) {

    fun invoke(contact: Contact) {
        repo.remove(contact)
    }
}