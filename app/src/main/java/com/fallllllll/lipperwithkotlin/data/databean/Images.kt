package com.fallllllll.lipperwithkotlin.data.databean


import com.google.gson.annotations.SerializedName


data class Images(

	@field:SerializedName("normal")
	val normal: String? = null,

	@field:SerializedName("hidpi")
	val hidpi: String? = null,

	@field:SerializedName("teaser")
	val teaser: String? = null
)