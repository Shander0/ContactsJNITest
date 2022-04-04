package com.example.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "contacts")
data class Contact (
    @PrimaryKey val phone: String = "",
    val name: String = ""
): Serializable