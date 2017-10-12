package com.fallllllll.lipperwithkotlin.data.local.user

import com.google.gson.annotations.SerializedName

/**
 * Created by fall on 2017/6/2/002.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
class UserToken {
    val access_token: String?=null
    val token_type: String?=null
    val scope: String ?=null
    val created_at: Int ?=null
}

data class LipperUser(

        @field:SerializedName("comments_received_count")
        val commentsReceivedCount: Int? = null,

        @field:SerializedName("buckets_url")
        val bucketsUrl: String? = null,

        @field:SerializedName("following_url")
        val followingUrl: String? = null,

        @field:SerializedName("bio")
        val bio: String? = null,

        @field:SerializedName("projects_count")
        val projectsCount: Int? = null,

        @field:SerializedName("created_at")
        val createdAt: String? = null,

        @field:SerializedName("type")
        val type: String? = null,

        @field:SerializedName("updated_at")
        val updatedAt: String? = null,

        @field:SerializedName("shots_url")
        val shotsUrl: String? = null,

        @field:SerializedName("links")
        val links: Links? = null,

        @field:SerializedName("id")
        val id: Int? = null,

        @field:SerializedName("teams_count")
        val teamsCount: Int? = null,

        @field:SerializedName("can_upload_shot")
        val canUploadShot: Boolean? = null,

        @field:SerializedName("likes_url")
        val likesUrl: String? = null,

        @field:SerializedName("likes_received_count")
        val likesReceivedCount: Int? = null,

        @field:SerializedName("pro")
        val pro: Boolean? = null,

        @field:SerializedName("followers_url")
        val followersUrl: String? = null,

        @field:SerializedName("buckets_count")
        val bucketsCount: Int = 0,

        @field:SerializedName("followings_count")
        val followingsCount: Int = 0,

        @field:SerializedName("rebounds_received_count")
        val reboundsReceivedCount: Int = 0,

        @field:SerializedName("likes_count")
        val likesCount: Int = 0,

        @field:SerializedName("teams_url")
        val teamsUrl: String? = null,

        @field:SerializedName("avatar_url")
        val avatarUrl: String? = null,

        @field:SerializedName("html_url")
        val htmlUrl: String? = null,

        @field:SerializedName("followers_count")
        val followersCount: Int? = null,

        @field:SerializedName("name")
        val name: String? = null,

        @field:SerializedName("location")
        val location: String? = null,

        @field:SerializedName("shots_count")
        val shotsCount: Int? = null,

        @field:SerializedName("username")
        val username: String? = null
)

data class Links(

        @field:SerializedName("twitter")
        val twitter: String? = null,

        @field:SerializedName("web")
        val web: String? = null
)