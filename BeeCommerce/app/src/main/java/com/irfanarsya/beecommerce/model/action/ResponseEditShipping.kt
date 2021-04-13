package com.irfanarsya.beecommerce.model.action

import com.google.gson.annotations.SerializedName

data class ResponseEditShipping(

	@field:SerializedName("response_status")
	val responseStatus: Int? = null,

	@field:SerializedName("data")
	val data: DataES? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
)

data class DataES(

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("province")
	val province: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("is_main_address")
	val isMainAddress: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("zip_code")
	val zipCode: String? = null
)
