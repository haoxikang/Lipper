package com.fallllllll.lipperwithkotlin.data.network.model.impl

import com.fallllllll.lipperwithkotlin.BuildConfig
import com.fallllllll.lipperwithkotlin.core.MyRobolectricTestRunner
import com.fallllllll.lipperwithkotlin.core.rxjava.ConvertToAPIException
import com.fallllllll.lipperwithkotlin.data.databean.ShotBean
import com.fallllllll.lipperwithkotlin.data.local.user.LipperUser
import com.fallllllll.lipperwithkotlin.data.local.user.UserManager
import com.fallllllll.lipperwithkotlin.utils.RxSchedulersOverrideRule
import com.fallllllll.lipperwithkotlin.utils.initUser
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Created by fallllllll on 2017/6/21/021.
 * GitHub :  https://github.com/348476129/Lipper
 */
@RunWith(MyRobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(23))
class DribbbleModelImplTest {
    @Rule
    @JvmField
    var mRxSchedulersOverrideRule = RxSchedulersOverrideRule()
    lateinit var disposable: Disposable
    var list: List<ShotBean>? = null
    var lipperUser: LipperUser? = null
    @Before
    fun beforeTest() {
        initUser()
    }

    @Test
    fun getShot() {
        disposable = DribbbleModelImpl.getInstance().getShot("", "", "", "1")
                .onErrorResumeNext(ConvertToAPIException())
                .subscribeBy({
                    list = it
                }, {})
        assertNotNull(list)
    }

    @Test
    fun getUserInfo() {
        disposable = DribbbleModelImpl.getInstance().getUserInfo(UserManager.get().access_token)
                .onErrorResumeNext(ConvertToAPIException())
                .subscribeBy({
                    lipperUser = it
                    print(lipperUser?.avatarUrl)
                }, {})
        assertNotNull(lipperUser)
    }

    @After
    fun afterTest() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }
}