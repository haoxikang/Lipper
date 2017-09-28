package com.fallllllll.lipperwithkotlin.data.databean

import com.google.gson.annotations.SerializedName

/**
 * Created by qqq34 on 2017/9/28.
 */
data class UserLikesBean(
        @field:SerializedName("id")
        val id: Long? = null,

        @field:SerializedName("created_at")
        val createdAt: String? = null,

        @field:SerializedName("shot")
        val shot: ShotBean? = null
)