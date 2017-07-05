package com.fallllllll.lipperwithkotlin.data.network.model

import com.fallllllll.lipperwithkotlin.data.databean.ShotBean
import io.reactivex.Flowable

/**
 * Created by fallllllll on 2017/7/5/005.
 * GitHub :  https://github.com/348476129/Lipper
 */
interface SearchModel {
    fun search(query:String, sort: String, page: String): Flowable<List<ShotBean>>
}