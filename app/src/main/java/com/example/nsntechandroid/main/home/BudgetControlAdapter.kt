package com.example.nsntechandroid.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_budget_kontrol.view.*
import com.example.nsntechandroid.R
import com.example.nsntechandroid.extension.toRupiahFormat
import com.example.nsntechandroid.extension.persenOf


class BudgetControlAdapter(val items: List<BudgetControl>) :
    RecyclerView.Adapter<BudgetControlAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_budget_kontrol, parent, false)
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
        fun bind(item: BudgetControl) = with(view) {
            emoji.text = item.emoji
            txtBudgetTitel.text = item.nama
            txtBudgetAmount.text = item.amount.toRupiahFormat()
            progressBujet.progress = item.curAmount.persenOf(item.amount)
        }
    }

}