package com.fallllllll.lipperwithkotlin.ui.main.home

import com.fallllllll.lipperwithkotlin.BuildConfig
import com.fallllllll.lipperwithkotlin.core.MyRobolectricTestRunner
import com.fallllllll.lipperwithkotlin.core.constants.*
import com.fallllllll.lipperwithkotlin.core.rxjava.RxBus
import com.fallllllll.lipperwithkotlin.data.databean.HomeListFilterBean
import com.fallllllll.lipperwithkotlin.data.databean.eventBean.LoginEvent
import com.fallllllll.lipperwithkotlin.data.local.datatank.DelegatesExt
import com.fallllllll.lipperwithkotlin.utils.initUser
import com.fallllllll.lipperwithkotlin.utils.mock
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config


/**
 * Created by fallllllll on 2017/6/22/022.
 * GitHub :  https://github.com/348476129/Lipper
 */
@RunWith(MyRobolectricTestRunner::class)
@Config(constants = BuildConfig::class,sdk = intArrayOf(23))
class ShotsActivityPresenterTest {

    lateinit var presenter: ShotsActivityContract.ShotsActivityPresenter
    val view = mock<ShotsActivityContract.ShotsActivityView>()
    @Before
    fun beforeTest() {
        presenter = ShotsActivityPresenter(view)
        presenter.attach()
        var time: String by DelegatesExt.valuePreference(KEY_FILTER_TIME, "")
        var sort: String by DelegatesExt.valuePreference(KEY_FILTER_SORT, "")
        var type: String by DelegatesExt.valuePreference(KEY_FILTER_TYPE, "")
        time = WEEK
        sort = RECENT
        type = DEBUTS
    }

    @Test
    fun textSubscribeLogEvent() {
        presenter.onPresenterCreate()
        RxBus.get().post(LoginEvent(true))
        verify(view, times(2)).showUserUI(any())
        RxBus.get().post(LoginEvent(false))
        verify(view).LogOut()
    }

    @Test
    fun testOnCreate() {
        initUser()
        presenter.onPresenterCreate()
        verify(view).showUserUI(any())
    }

    @Test
    fun showBottomSheet() {


        presenter.showBottomSheet()

        val argumentCaptor = argumentCaptor<HomeListFilterBean>()
        verify(view).showBottomSheet(argumentCaptor.capture())


        assertEquals(WEEK, argumentCaptor.firstValue.time)
        assertEquals(RECENT, argumentCaptor.firstValue.sort)
        assertEquals(DEBUTS, argumentCaptor.firstValue.type)

    }

    @Test
    fun testUserImageViewClick() {
        presenter.menuActivityClick()
        verify(view).goDribbbeLoginActivity()
        initUser()
        presenter.menuActivityClick()
        verify(view).goUserActivity()
    }
}