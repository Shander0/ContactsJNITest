package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.domain.dao.ContactsDao
import com.example.domain.entities.Contact

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract val contactsDao: ContactsDao
}