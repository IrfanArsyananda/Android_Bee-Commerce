package com.irfanarsya.beecommerce.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irfanarsya.beecommerce.R
import com.irfanarsya.beecommerce.model.ShippingAddressItem
import kotlinx.android.synthetic.main.item_shipping.view.*

class ShippingAdapter(val data: List<ShippingAddressItem>?, val itemClick: OnClickListener): RecyclerView.Adapter<ShippingAdapter.ShippingHolder>() {

    class ShippingHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.itemTitleShipping
        val address =  itemView.itemAlamatShip
        val utama = itemView.statUtama
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShippingHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shipping,parent,false)
        val holder = ShippingHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ShippingHolder, position: Int) {
        val item = data?.get(position)

        holder.title.text = item?.title
        holder.address.text = item?.address
        if (item?.isMainAddress == "1"){
            holder.utama.visibility = View.VISIBLE
        }

        holder.itemView.setOnClickListener {
            itemClick.edit(item)
        }

    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    interface OnClickListener {
        fun edit(item: ShippingAddressItem?)
    }
}