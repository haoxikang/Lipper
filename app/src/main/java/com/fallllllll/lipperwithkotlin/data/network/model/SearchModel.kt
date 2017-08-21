package com.fallllllll.lipperwithkotlin.data.network.model

import com.fallllllll.lipperwithkotlin.data.databean.ShotBean
import io.reactivex.Flowable

/**
 * Created by fall on 2017/7/5/005.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
interface SearchModel {
    fun search(query:String, sort: String, page: String): Flowable<List<ShotBean>>
}