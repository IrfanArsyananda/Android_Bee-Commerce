package com.irfanarsya.beecommerce.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.irfanarsya.beecommerce.R
import com.irfanarsya.beecommerce.model.DataItem
import com.irfanarsya.beecommerce.network.Constant
import com.irfanarsya.beecommerce.view.detailProduct.DetailActivity
import kotlinx.android.synthetic.main.item_home.view.*

class HomeAdapter : PagedListAdapter<DataItem, RecyclerView.ViewHolder>(DataItem().DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home,parent,false)
        return HomeHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HomeHolder){
            holder.bindTo(getItem(position))
            holder.itemView(getItem(position))
        }
    }

    class HomeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindTo(item: DataItem?) {

            itemView.itemNameProduct.text = item?.title
            itemView.itemPriceProduct.text = "Rp. ${item?.price},-"

            var photos = item?.productPhotos
            if (photos!!.isNotEmpty()) {
                var url = photos.get(0)?.fileName
                val urlEnd = Constant.BASE_IMG_PRO+"$url"
                Glide.with(itemView.context).load(urlEnd).into(itemView.itemImageProduct)
            }

        }

        fun itemView(item: DataItem?){
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra("id", item?.id.toString())
                itemView.context.startActivity(intent)
            }
        }

    }

}