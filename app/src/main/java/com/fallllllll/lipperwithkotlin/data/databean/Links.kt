package com.fallllllll.lipperwithkotlin.data.databean

import com.google.gson.annotations.SerializedName


data class Links(

	@field:SerializedName("twitter")
	val twitter: String? = null,

	@field:SerializedName("web")
	val web: String? = null
)