package com.fallllllll.lipperwithkotlin.data.network.model

import com.fallllllll.lipperwithkotlin.data.databean.ShotBean
import com.fallllllll.lipperwithkotlin.data.local.user.LipperUser
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by fall on 2017/6/2/002.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
interface DribbbleModel {
     fun getShot(list: String, timeFrame: String, sort: String, page: String): Flowable<List<ShotBean>>

     fun getUserInfo(token:String): Flowable<LipperUser>

    fun getUserLikes(userId: String): Flowable<List<ShotBean>>
}