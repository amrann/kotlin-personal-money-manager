package com.example.nsntechandroid.main.report

import android.system.Os.bind
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.nsntechandroid.R
import com.example.nsntechandroid.extension.toRupiahFormat
import com.example.nsntechandroid.main.home.TransactionControl
import kotlinx.android.synthetic.main.item_spending.view.*
import kotlinx.android.synthetic.main.item_transaction.view.*

class SpendingAdapter() : RecyclerView.Adapter<SpendingAdapter.ViewHolder>(){

    private val items: MutableList<TransactionControl> = mutableListOf()

    fun updateData(updateList: List<TransactionControl>) {
        this.items.apply {
            clear()
            addAll(updateList)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_spending, parent, false)
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
            emojiSpending.text = item.category?.emoji
            txtNama.text = item.category?.name
            txtNominal.text = item.nominal?.toRupiahFormat()

            val colorText = when (item.category?.tipe) {
                "Expense" -> ContextCompat.getColor(context, R.color.red)
                else -> ContextCompat.getColor(context, R.color.green)
            }

            val colorBg = when (item.category?.tipe) {
                "Expense" -> ContextCompat.getColor(context, R.color.softRed)
                else -> ContextCompat.getColor(context, R.color.softGreen)
            }

            txtNominal.setTextColor(colorText)
            txtNama.setTextColor(colorText)
            cardTransaction.setCardBackgroundColor(colorBg)
        }
    }
}