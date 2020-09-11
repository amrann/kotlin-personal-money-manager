package com.example.nsntechandroid.main.home

import android.system.Os.bind
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nsntechandroid.R
import com.example.nsntechandroid.extension.toRupiahFormat
import kotlinx.android.synthetic.main.item_transaction.view.*

class TransactionControlAdapter(val items: List<TransactionControl>) :
    RecyclerView.Adapter<TransactionControlAdapter.ViewHolder>() {

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
            emojiTransaction.text = item.emoji
            txtTransactionTitel.text = item.nama
            txtAmount.text = item.nominal.toRupiahFormat()
            txtDate.text = item.tanggal
        }

    }

}