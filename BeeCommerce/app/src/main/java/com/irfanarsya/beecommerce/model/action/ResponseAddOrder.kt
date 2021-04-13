package com.irfanarsya.beecommerce.model.action

import com.google.gson.annotations.SerializedName

data class ResponseAddOrder(

	@field:SerializedName("data")
	val data: DataAO? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
)

data class DataAO(

	@field:SerializedName("order_code")
	val orderCode: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("user_id")
	val userId: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("shipping_address")
	val shippingAddress: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
