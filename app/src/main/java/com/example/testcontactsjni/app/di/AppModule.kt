package com.example.testcontactsjni.app.di

import android.app.Application
import androidx.room.Room
import com.example.data.db.AppDatabase
import com.example.domain.dao.ContactsDao
import com.example.testcontactsjni.viewmodel.MainActivityViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {

    viewModel { MainActivityViewModel(
        contactPhoneValidator = get(),
        createContactUseCase = get(),
        getContactsUseCase = get(),
        deleteContactUseCase = get(),
        get()
    ) }

    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "test.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    fun provideDao(database: AppDatabase): ContactsDao{
        return database.contactsDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }

}