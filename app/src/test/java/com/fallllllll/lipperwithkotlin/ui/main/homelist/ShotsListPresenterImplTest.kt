package com.fallllllll.lipperwithkotlin.ui.main.homelist

import com.fall.generalrecyclerviewfragment.GeneralContract
import com.fallllllll.lipperwithkotlin.BuildConfig
import com.fallllllll.lipperwithkotlin.core.MyRobolectricTestRunner
import com.fallllllll.lipperwithkotlin.core.constants.NOW
import com.fallllllll.lipperwithkotlin.core.constants.POPULARITY
import com.fallllllll.lipperwithkotlin.core.constants.SHOTS
import com.fallllllll.lipperwithkotlin.core.constants.SHOTS_LAYOUT_ONLY_IMAGE
import com.fallllllll.lipperwithkotlin.core.rxjava.RxBus
import com.fallllllll.lipperwithkotlin.data.databean.ShotBean
import com.fallllllll.lipperwithkotlin.data.databean.eventBean.ShotsListFilterEvent
import com.fallllllll.lipperwithkotlin.data.databean.eventBean.ShotsMenuLayoutEvent
import com.fallllllll.lipperwithkotlin.data.network.model.impl.DribbbleModelImpl
import com.fallllllll.lipperwithkotlin.utils.RxSchedulersOverrideRule
import com.fallllllll.lipperwithkotlin.utils.mock
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Created by fallllllll on 2017/6/22/022.
 * GitHub :  https://github.com/348476129/Lipper
 */
@RunWith(MyRobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class ShotsListPresenterImplTest {
    @Rule
    @JvmField
    var mRxSchedulersOverrideRule = RxSchedulersOverrideRule()

    val view = mock<GeneralContract.View>()

    val shotsListView = mock<ShotsListContract.ShotsListView>()
    lateinit var presenter: ShotsListPresenterImpl
    @Before
    fun beforeTest() {
        presenter = spy(ShotsListPresenterImpl(DribbbleModelImpl.getInstance(), shotsListView))
        presenter.attach()
        presenter.setView(view)
    }

    @Test
    fun refreshData() {
        presenter.refreshData()
        verify(presenter).refreshFinish(any<List<ShotBean>>())
        verify(view).closeLoadAnimation()
        verify(view).refreshFinish(any<List<ShotBean>>())

    }

    @Test
    fun loadNextPageData() {
        presenter.loadNextPageData(2)
        verify(presenter).loadNextPageFinish(any<List<ShotBean>>())
        verify(view).closeLoadAnimation()
        verify(view).loadNextDataFinish(any<List<ShotBean>>())
    }

    @Test
    fun testRxBus(){
        presenter.initRxBus()
        RxBus.get().post(ShotsListFilterEvent(NOW, POPULARITY, SHOTS))
        verify(presenter).checkAndRefreshData()
        verify(presenter).stopLoading()
        RxBus.get().post(ShotsMenuLayoutEvent(SHOTS_LAYOUT_ONLY_IMAGE))
        verify(presenter).setListLayout(any<String>())
    }
}