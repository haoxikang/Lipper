package com.fallllllll.lipperwithkotlin.data.network.service

import com.fallllllll.lipperwithkotlin.data.databean.ShotBean
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by fallllllll on 2017/7/5/005.
 * GitHub :  https://github.com/348476129/Lipper
 */
interface SearchService {
    @GET("search")
    fun search( @Query("q") query: String, @Query("s") sort: String, @Query("page") page: String, @Query("per_page") perPage: String): Flowable<List<ShotBean>>
}