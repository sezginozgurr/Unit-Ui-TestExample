package com.example.ui_unittest_android.utils

object Validator {
    fun validateInput(amount: Int, description: String) =
        !(amount <= 0 || description.isEmpty())
}