package com.example.nsntechandroid.main.home

import com.example.nsntechandroid.main.transaction.dialog.Category
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class TransactionControl (
    val nominal: Int? = 0,
    val tanggal: String? = "",
    val category: Category?
)