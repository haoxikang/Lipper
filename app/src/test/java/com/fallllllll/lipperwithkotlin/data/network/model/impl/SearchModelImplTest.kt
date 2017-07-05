package com.fallllllll.lipperwithkotlin.data.network.model.impl

import com.fallllllll.lipperwithkotlin.BuildConfig
import com.fallllllll.lipperwithkotlin.core.MyRobolectricTestRunner
import com.fallllllll.lipperwithkotlin.core.rxjava.ConvertToAPIException
import com.fallllllll.lipperwithkotlin.data.databean.ShotBean
import com.fallllllll.lipperwithkotlin.utils.LogUtils
import com.fallllllll.lipperwithkotlin.utils.RxSchedulersOverrideRule
import io.reactivex.rxkotlin.subscribeBy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog

/**
 * Created by fallllllll on 2017/7/5/005.
 * GitHub :  https://github.com/348476129/Lipper
 */
@RunWith(MyRobolectricTestRunner::class)
@Config(constants = BuildConfig::class,sdk = intArrayOf(23))
class SearchModelImplTest {
    @Rule
    @JvmField
    var mRxSchedulersOverrideRule = RxSchedulersOverrideRule()
    var list: List<ShotBean>? = null
    @Test
    fun getSearchService() {
        ShadowLog.stream = System.out;
        SearchModelImpl.getInstance().search("a","","1")
                .onErrorResumeNext(ConvertToAPIException())
                .subscribeBy({
                    list = it

                })
        assertNotNull(list)
    }

}