package com.irfanarsya.beecommerce.model

import com.google.gson.annotations.SerializedName

data class ResponseGetProfile(

	@field:SerializedName("datas")
	val datas: List<DatasItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
)

data class ShippingAddressItem(

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

data class DatasItem(

	@field:SerializedName("selected_shipping_city")
	val selectedShippingCity: String? = null,

	@field:SerializedName("selected_shipping_zip_code")
	val selectedShippingZipCode: String? = null,

	@field:SerializedName("selected_shipping_province")
	val selectedShippingProvince: String? = null,

	@field:SerializedName("last_name")
	val lastName: String? = null,

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("email_verified_at")
	val emailVerifiedAt: Any? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("selected_shipping_address")
	val selectedShippingAddress: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("shipping_address")
	val shippingAddress: List<ShippingAddressItem?>? = null,

	@field:SerializedName("first_name")
	val firstName: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
