package com.irfanarsya.beecommerce.model

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class ResponseGetCategory(

	@field:SerializedName("getCategory")
	val getCategory: List<CategoryItem>
)

data class CategoryItem(

	@field:SerializedName("cover")
	val cover: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: Any? = null,

	@field:SerializedName("description")
	val description: Any? = null,

	@field:SerializedName("created_at")
	val createdAt: Any? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	var DIFF_CALLBACK: DiffUtil.ItemCallback<CategoryItem> = object  : DiffUtil.ItemCallback<CategoryItem>(){
		override fun areItemsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
			return oldItem.title == newItem.title
		}

		override fun areContentsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
			return oldItem.equals(newItem)
		}

	}
)
