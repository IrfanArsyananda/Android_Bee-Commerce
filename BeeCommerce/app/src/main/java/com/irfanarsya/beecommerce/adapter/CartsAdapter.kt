package com.irfanarsya.beecommerce.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.irfanarsya.beecommerce.R
import com.irfanarsya.beecommerce.model.DataItemC
import com.irfanarsya.beecommerce.network.Constant
import kotlinx.android.synthetic.main.item_cart.view.*

class CartsAdapter(val data: List<DataItemC>?, val itemClick: OnClickListener): RecyclerView.Adapter<CartsAdapter.CartsHolder>() {

    class CartsHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
        val img = itemView.itemImageCart
        val title =  itemView.itemNamaCart
        val qty = itemView.itemQtyCart
        val price = itemView.itemHargaCart

        val delete = itemView.btnDeleteCart
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart,parent,false)
        val holder = CartsHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: CartsHolder, position: Int) {
        val item = data?.get(position)

        holder.title.text = item?.product?.title
        holder.qty.text = "Jumlah : ${item?.qty.toString()}"
        holder.price.text = "Rp. ${item?.product?.price},-"

        val photos = item?.product?.productPhotos
        var url = photos?.get(0)?.fileName
        val urlEnd = Constant.BASE_IMG_PRO+"$url"
        Glide.with(holder.itemView.context).load(urlEnd).into(holder.img)

        holder.img.setOnClickListener {
            itemClick.detail(item)
        }
        holder.itemView.setOnClickListener {
            itemClick.update(item)
        }
        holder.delete.setOnClickListener {
            itemClick.delete(item)
        }

    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    interface OnClickListener {
        fun detail(item: DataItemC?)
        fun update(item: DataItemC?)
        fun delete(item: DataItemC?)
    }
}