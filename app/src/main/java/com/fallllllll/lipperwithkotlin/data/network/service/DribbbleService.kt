package com.fallllllll.lipperwithkotlin.data.network.service

import com.fall.retrofitannotation.RetrofitService
import com.fallllllll.lipperwithkotlin.core.constants.BASE_URL
import com.fallllllll.lipperwithkotlin.data.databean.NormalBean
import com.fallllllll.lipperwithkotlin.data.databean.ShotBean
import com.fallllllll.lipperwithkotlin.data.databean.UserLikesBean
import com.fallllllll.lipperwithkotlin.data.local.user.LipperUser
import io.reactivex.Flowable
import okhttp3.RequestBody
import retrofit2.http.*

/**
 * Created by fall on 2017/6/2/002.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
@RetrofitService(BASE_URL)
interface DribbbleService {
    @GET("shots")
    fun getShots(@Query("list") list: String, @Query("timeframe") timeframe: String, @Query("sort") sort: String, @Query("page") page: String, @Query("per_page") perPage: String): Flowable<List<ShotBean>>

    @GET("user")
    fun getUserInfo(@Header("Authorization") token: String): Flowable<LipperUser>

    @POST("shots/{shotsId}/like")
    fun likeAShot(@Path("shotsId") shotsId: String): Flowable<UserLikesBean>

    @DELETE("shots/{shotsId}/like")
    fun unlikeAShot(@Path("shotsId") shotsId: String): Flowable<RequestBody>

    @GET("users/{userId}/likes")
    fun getUserLikes(@Path("userId") userId: String): Flowable<List<UserLikesBean>>

    @GET("users/following/shots")
    fun getUserFollowingShots(@Query("page") page: String, @Query("per_page") perPage: String):Flowable<List<ShotBean>>

    @GET("user/shots")
    fun getUserShots(@Query("page") page: String, @Query("per_page") perPage: String):Flowable<List<ShotBean>>


}