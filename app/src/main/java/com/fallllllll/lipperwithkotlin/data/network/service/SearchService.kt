package com.fallllllll.lipperwithkotlin.data.network.service

import android.support.annotation.StringDef
import com.fallllllll.lipperwithkotlin.core.constants.SORT_POPULAR
import com.fallllllll.lipperwithkotlin.core.constants.SORT_RECENT
import com.fallllllll.lipperwithkotlin.data.databean.ShotBean
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * Created by fall on 2017/7/5/005.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */


interface SearchService {

    @Retention(AnnotationRetention.SOURCE)
    @StringDef(SORT_POPULAR, SORT_RECENT)
    annotation class SortOrder

    @GET("search")
    fun search(@Query("q") query: String, @Query("s") @SortOrder sort: String, @Query("page") page: String, @Header("Accept-Encoding") header: String): Flowable<List<ShotBean>>
}