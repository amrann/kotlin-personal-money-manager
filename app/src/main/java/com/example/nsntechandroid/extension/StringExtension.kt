package com.example.nsntechandroid.extension

import java.text.SimpleDateFormat
import java.util.*

fun String.toDefaultTanggalFormat(): String {
    val curDate = Calendar.getInstance().time
    curDate.time = this.ifBlank { "1598908136284" }.toLong()
    val format = SimpleDateFormat("EEE, d MM yyyy", Locale.getDefault())
    return format.format(curDate)
}

fun String.timeMillisToTanggal(): Calendar {
    val curDate = Calendar.getInstance()
    val tgl = Date()
    tgl.time = this.ifBlank { "1598908136284" }.toLong()
    curDate.time = tgl
    return curDate
}