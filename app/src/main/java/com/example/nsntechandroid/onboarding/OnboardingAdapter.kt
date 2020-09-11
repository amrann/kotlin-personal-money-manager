package com.example.nsntechandroid.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nsntechandroid.R
import kotlinx.android.synthetic.main.item_onbording.view.*

class OnboardingAdapter(val items: List<Onboarding>) : RecyclerView.Adapter<OnboardingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.item_onbording,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            bind(items[position])
        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Onboarding) = with(view){
            imgDisplay.setImageResource(item.image)
            txtTitel.setText(item.title)
            txtSubtitel.setText(item.subtitle)
        }
    }

}