package com.example.nsntechandroid.main.home

import android.system.Os.bind
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.nsntechandroid.R
import com.example.nsntechandroid.extension.toDefaultTanggalFormat
import com.example.nsntechandroid.extension.toRupiahFormat
import com.example.nsntechandroid.main.transaction.dialog.Category
import kotlinx.android.synthetic.main.item_transaction.view.*

class TransactionControlAdapter() : RecyclerView.Adapter<TransactionControlAdapter.ViewHolder>() {
    private val items: MutableList<TransactionControl> = mutableListOf()

    fun updateData(updateList: List<TransactionControl>) {
        this.items.apply {
            clear()
            addAll(updateList)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            bind(items[position])
        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: TransactionControl) = with(view) {
            emojiTransaction.text = item.category?.emoji
            txtTransactionTitel.text = item.category?.name
            txtAmount.text = item.nominal?.toRupiahFormat()
            txtDate.text = item.tanggal?.toDefaultTanggalFormat()

            val color = when(item.category?.tipe) {
                "Expense" -> ContextCompat.getColor(context, R.color.red)
                else -> ContextCompat.getColor(context, R.color.green)
            }
            txtAmount.setTextColor(color)
        }
    }
}