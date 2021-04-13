package com.irfanarsya.beecommerce.model.action

import com.google.gson.annotations.SerializedName

data class ResponseDeleteCartItem(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
)
