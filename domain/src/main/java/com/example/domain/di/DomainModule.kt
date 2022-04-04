package com.example.domain.di

import com.example.domain.usecase.CreateContactUseCase
import com.example.domain.usecase.DeleteContactUseCase
import com.example.domain.usecase.GetContactsUseCase
import org.koin.dsl.module

val DomainModule = module {

    factory { GetContactsUseCase(get()) }
    factory { CreateContactUseCase(get()) }
    factory { DeleteContactUseCase(get()) }
}