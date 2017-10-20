package com.fallllllll.lipperwithkotlin.data.databean

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Links(

	@field:SerializedName("twitter")
	val twitter: String? = null,

	@field:SerializedName("web")
	val web: String? = null
):Serializable