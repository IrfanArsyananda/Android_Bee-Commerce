package com.irfanarsya.beecommerce.model

import com.google.gson.annotations.SerializedName

data class ResponseDetailProduct(

	@field:SerializedName("response_status")
	val responseStatus: Int? = null,

	@field:SerializedName("data")
	val data: DataD? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
)

data class ProductPhotosItemD(

	@field:SerializedName("updated_at")
	val updatedAt: Any? = null,

	@field:SerializedName("file_name")
	val fileName: String? = null,

	@field:SerializedName("product_id")
	val productId: Int? = null,

	@field:SerializedName("is_cover")
	val isCover: String? = null,

	@field:SerializedName("created_at")
	val createdAt: Any? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Category(

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
	val title: String? = null
)

data class DataD(

	@field:SerializedName("rating")
	val rating: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("product_code")
	val productCode: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("show_in_slider")
	val showInSlider: String? = null,

	@field:SerializedName("category_id")
	val categoryId: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("price")
	val price: String? = null,

	@field:SerializedName("qty")
	val qty: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("product_photos")
	val productPhotos: List<ProductPhotosItemD?>? = null,

	@field:SerializedName("category")
	val category: Category? = null,

	@field:SerializedName("product_cover")
	val productCover: Any? = null
)
