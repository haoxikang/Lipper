package com.fallllllll.lipperwithkotlin.data.databean


import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class ShotBean(


        @field:SerializedName("rebound_source_url")
        val reboundSourceUrl: String? = null,

        @field:SerializedName("buckets_url")
        val bucketsUrl: String? = null,

        @field:SerializedName("rebounds_url")
        val reboundsUrl: String? = null,

        @field:SerializedName("rebounds_count")
        val reboundsCount: Int? = null,

        @field:SerializedName("description")
        val description: String? = null,

        @field:SerializedName("created_at")
        val createdAt: Date? = null,

        @field:SerializedName("title")
        val title: String? = null,

        @field:SerializedName("attachments_url")
        val attachmentsUrl: String? = null,

        @field:SerializedName("updated_at")
        val updatedAt: String? = null,

        @field:SerializedName("comments_url")
        val commentsUrl: String? = null,

        @field:SerializedName("id")
        val id: Long? = null,

        @field:SerializedName("views_count")
        val viewsCount: Int? = null,

        @field:SerializedName("height")
        val height: Int? = null,

        @field:SerializedName("images")
        val images: Images? = null,

        @field:SerializedName("likes_url")
        val likesUrl: String? = null,

        @field:SerializedName("team")
        val team: Team? = null,

        @field:SerializedName("buckets_count")
        val bucketsCount: Int? = null,

        @field:SerializedName("tags")
        val tags: List<String>? = null,

        @field:SerializedName("likes_count")
        var likesCount: Int = 0,

        @field:SerializedName("comments_count")
        val commentsCount: Int? = null,

        @field:SerializedName("html_url")
        val htmlUrl: String? = null,

        @field:SerializedName("width")
        val width: Int? = null,

        @field:SerializedName("animated")
        val animated: Boolean = false,

        @field:SerializedName("attachments_count")
        val attachmentsCount: Int? = null,

        @field:SerializedName("projects_url")
        val projectsUrl: String? = null,

        @field:SerializedName("user")
        val user: User? = null,
        var isLike:Boolean = false
):Serializable