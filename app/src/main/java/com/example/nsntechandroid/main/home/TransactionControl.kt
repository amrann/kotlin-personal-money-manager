package com.example.nsntechandroid.main.home

import com.example.nsntechandroid.extension.timeMillisToTanggal
import com.example.nsntechandroid.main.transaction.dialog.Category
import com.google.firebase.database.IgnoreExtraProperties
import java.util.*

@IgnoreExtraProperties
data class TransactionControl (
    val nominal: Int? = 0,
    val tanggal: String? = "",
    val category: Category? = null,
    var key: String? = ""
)

// filter default bulan sekarang
val List<TransactionControl>.bulanIni: List<TransactionControl>
    get() = filter {
        val curDate = Calendar.getInstance()
        val dateCalendar = it.tanggal?.timeMillisToTanggal()
        dateCalendar?.get(Calendar.MONTH) == curDate.get(Calendar.MONTH) && dateCalendar.get(
            Calendar.YEAR
        ) == curDate.get(Calendar.YEAR)
    }

// filter default mingguan
val List<TransactionControl>.mingguIni: List<TransactionControl>
    get() = filter{
        val curDate = Calendar.getInstance()
        it.tanggal?.timeMillisToTanggal()?.get(Calendar.WEEK_OF_YEAR) == curDate.get(Calendar.WEEK_OF_YEAR)
    }


val List<TransactionControl>.pendapatanBulanan: Int
    get() = bulanIni.filter {
        it.category?.tipe == "income"
    }.sumBy { it.nominal ?: 0 }



val List<TransactionControl>.pengeluaranBulanan: Int
    get() = bulanIni.filter {
        it.category?.tipe == "expense"
    }.sumBy { it.nominal ?: 0 }


val List<TransactionControl>.budgetCtrlBulanan: List<BudgetControl>
    get() = bulanIni
        .filter { it.category?.tipe == "expense" }
        .distinctBy { it.category?.key }
        .groupBy { it.category }.map {
            BudgetControl(
                it.key?.emoji ?: "",
                it.key?.name ?: "",
                it.key?.limit ?: 0,
                it.value.sumBy { it.nominal ?: 0 }
            )
        }


val List<TransactionControl>.laporanBulanan: List<TransactionControl>
    get() = bulanIni
        .distinctBy { it.category?.key }
        .groupBy { it.category }.map {
            TransactionControl(
                it.value.sumBy { it.nominal ?: 0 },
                "",
                it.key
            )
        }.sortedByDescending { it.nominal }


val List<TransactionControl>.filterBulanan: List<Category>
    get() = bulanIni.distinctBy { it.category?.key }
        .groupBy { it.category }.map {
            it.key?.selected = false
            it.key ?: Category()
        }
