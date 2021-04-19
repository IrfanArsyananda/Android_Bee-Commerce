package com.irfanarsya.beecommerce.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.irfanarsya.beecommerce.R
import com.irfanarsya.beecommerce.model.DataItemGO
import kotlinx.android.synthetic.main.item_order.view.*

class OrdersAdapter: PagedListAdapter<DataItemGO, RecyclerView.ViewHolder>(DataItemGO().DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order,parent,false)
        return OrdersHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is OrdersHolder){
            holder.bindTo(getItem(position))
            holder.itemView(getItem(position))
        }
    }

    class OrdersHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindTo(item: DataItemGO?) {

            itemView.itemCodeOrder.text = item?.orderCode
            itemView.itemTanggalOrder.text = item?.createdAt
            val statPaid = item?.orderStatus
            if (statPaid == "PAID"){
                itemView.itemPaid.setText("TERBAYAR")
            }else{
                itemView.itemPaid.setText("BELUM BAYAR")
            }

        }

        fun itemView(item: DataItemGO?){
            itemView.setOnClickListener {
//                val intent = Intent(itemView.context, DetailActivity::class.java)
//                intent.putExtra("id", item?.id.toString())
//                itemView.context.startActivity(intent)
            }
        }

    }

}