package com.irfanarsya.beecommerce.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.irfanarsya.beecommerce.R
import com.irfanarsya.beecommerce.model.CategoryItem
import com.irfanarsya.beecommerce.model.DataItemGO
import com.irfanarsya.beecommerce.network.Constant
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(val itemClick: OnClickListener) : PagedListAdapter<CategoryItem, RecyclerView.ViewHolder>(CategoryItem().DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return HomeHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HomeHolder) {
            holder.bindTo(getItem(position))
        }
        holder.itemView.setOnClickListener {
            itemClick.detail(getItem(position))
        }
    }

    class HomeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindTo(item: CategoryItem?) {

            itemView.itemNameCategory.text = item?.title

            var url = item?.cover
            val urlEnd = Constant.BASE_IMG_PRO + "$url"
            Glide.with(itemView.context).load(urlEnd).into(itemView.itemImageCategory)

        }
    }

    interface OnClickListener {
        fun detail(item: CategoryItem?)
    }

}