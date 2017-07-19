package com.fallllllll.lipperwithkotlin.data.network.model

import com.fallllllll.lipperwithkotlin.data.databean.ShotBean
import com.fallllllll.lipperwithkotlin.data.local.user.LipperUser
import io.reactivex.Flowable

/**
 * Created by fallllllll on 2017/6/2/002.
 * GitHub :  https://github.com/348476129/Lipper
 */
interface DribbbleModel {
     fun getShot(list: String, timeframe: String, sort: String, page: String): Flowable<List<ShotBean>>

     fun getUserInfo(token:String): Flowable<LipperUser>
}