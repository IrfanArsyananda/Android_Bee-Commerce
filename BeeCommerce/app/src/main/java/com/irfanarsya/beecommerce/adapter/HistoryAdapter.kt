package com.irfanarsya.beecommerce.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irfanarsya.beecommerce.R
import com.irfanarsya.beecommerce.local.History
import kotlinx.android.synthetic.main.item_history.view.*

class HistoryAdapter(private val data: List<History>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>(){
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        fun bind(item: History?){
            view.itemKeyword.text = item?.keyword
            view.itemDate.text = item?.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data.get(position)
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size?: 0
    }

}