package com.irfanarsya.beecommerce.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.irfanarsya.beecommerce.R
import com.irfanarsya.beecommerce.model.ProductsItem
import com.irfanarsya.beecommerce.network.Constant
import kotlinx.android.synthetic.main.item_home.view.*
import kotlinx.android.synthetic.main.item_orders_product.view.*

class OrderProductAdapter(val data: List<ProductsItem>?): RecyclerView.Adapter<OrderProductAdapter.OPHolder>() {

    class OPHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.itemNamaOrdersProduct
        val price =  itemView.itemHargaOrdersProduct
        val qty = itemView.itemQtyOrdersProduct
        val img = itemView.itemImageOrdersProduct
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OPHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_orders_product,parent,false)
        val holder = OPHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: OPHolder, position: Int) {
        val item = data?.get(position)

        holder.title.text = item?.title
        holder.price.text = item?.price
        holder.qty.text = item?.qtyOrder.toString()
        var photos = item?.cover
        if (photos!!.isNotEmpty()) {
            val urlEnd = Constant.BASE_IMG_PRO+"$photos"
            Glide.with(holder.itemView.context).load(urlEnd).into(holder.img)
        }

    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

}