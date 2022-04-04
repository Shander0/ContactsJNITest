package com.example.domain.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.example.domain.entities.Contact

@Dao
interface ContactsDao {
    @Query("SELECT * FROM contacts")
    fun getAll(): LiveData<List<Contact>>

    @Insert(onConflict = IGNORE)
    fun add(contact: Contact): Long

    @Delete(entity = Contact::class)
    fun remove(contact: Contact)
}