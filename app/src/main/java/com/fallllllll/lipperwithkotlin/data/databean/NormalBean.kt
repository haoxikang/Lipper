package com.fallllllll.lipperwithkotlin.data.databean

import com.google.gson.annotations.SerializedName

/**
 * Created by qqq34 on 2017/10/9.
 */
data class NormalBean(
        @field:SerializedName("id")
        val id: Long? = null,

        @field:SerializedName("created_at")
        val createdAt: String? = null
)