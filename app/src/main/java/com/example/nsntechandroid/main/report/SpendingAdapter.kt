package com.example.nsntechandroid.main.report

import android.system.Os.bind
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nsntechandroid.R
import com.example.nsntechandroid.extension.toRupiahFormat
import kotlinx.android.synthetic.main.item_spending.view.*

class SpendingAdapter(val items: List<Spending>) :
    RecyclerView.Adapter<SpendingAdapter.ViewHolder>(){

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
        fun bind(item: Spending) = with(view) {
            emojiSpending.text = item.emoji
            txtNama.text = item.nama
            txtNominal.text = item.nominal.toRupiahFormat()
        }
    }
}