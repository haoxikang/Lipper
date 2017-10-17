package com.fallllllll.lipperwithkotlin.data.network.model

import com.fallllllll.lipperwithkotlin.data.databean.ShotBean
import com.fallllllll.lipperwithkotlin.data.databean.UserLikesBean
import com.fallllllll.lipperwithkotlin.data.local.user.LipperUser
import io.reactivex.Flowable
import okhttp3.RequestBody

/**
 * Created by fall on 2017/6/2/002.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
interface DribbbleModel {
    fun getShot(list: String, timeFrame: String, sort: String, page: String): Flowable<List<ShotBean>>

    fun getUserInfo(token: String): Flowable<LipperUser>

    fun getUserLikes(userId: String): Flowable<List<UserLikesBean>>

    fun likeAShot(shotsId: String): Flowable<UserLikesBean>

    fun unlikeAShot(shotsId: String): Flowable<RequestBody>

    fun getUserFollowingShots(page: String): Flowable<List<ShotBean>>

    fun getUserShots(page: String): Flowable<List<ShotBean>>

}