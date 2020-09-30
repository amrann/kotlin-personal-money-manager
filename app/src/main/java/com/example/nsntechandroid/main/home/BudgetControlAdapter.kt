package com.example.nsntechandroid.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_budget_kontrol.view.*
import com.example.nsntechandroid.R
import com.example.nsntechandroid.extension.toRupiahFormat
import com.example.nsntechandroid.extension.persenOf
import com.example.nsntechandroid.main.transaction.dialog.Category


class BudgetControlAdapter : RecyclerView.Adapter<BudgetControlAdapter.ViewHolder>(){
    private val items: MutableList<BudgetControl> = mutableListOf()

    private var removedPosition: Int = 0
    private var removedItem: String = ""

    fun updateData(updateList: List<BudgetControl>) {
        this.items.apply {
            clear()
            addAll(updateList)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_budget_kontrol, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    // *** tambahan ***
//    fun removeItem(position: Int, viewHolder: RecyclerView.ViewHolder) {
//        removedItem = items[position]
//        removedPosition = position
//
//        items.removeAt(position)
//        notifyItemRemoved(position)
//
//        Snackbar.make(viewHolder.itemView, "$removedItem removed", Snackbar.LENGTH_LONG).setAction("UNDO") {
//            items.add(removedPosition, removedItem)
//            notifyItemInserted(removedPosition)
//
//        }.show()
//    }

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