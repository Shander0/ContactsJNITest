package com.example.data.validate

class ContactPhoneValidator {

    external fun validatePhone(stringToCheck: String): Boolean

    companion object {
        init {
            System.loadLibrary("data")
        }
    }
}