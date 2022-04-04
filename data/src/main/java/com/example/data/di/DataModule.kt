package com.example.data.di

import com.example.data.repo.ContactsRepositoryImpl
import com.example.data.validate.ContactPhoneValidator
import com.example.domain.repo.ContactsRepository
import org.koin.dsl.module

val DataModule = module{

    single<ContactsRepository> { ContactsRepositoryImpl(get()) }
    single { ContactPhoneValidator() }
}