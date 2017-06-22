package com.fallllllll.lipperwithkotlin.ui.main.home

import com.fallllllll.lipperwithkotlin.BuildConfig
import com.fallllllll.lipperwithkotlin.R
import com.fallllllll.lipperwithkotlin.core.MyRobolectricTestRunner
import com.fallllllll.lipperwithkotlin.data.databean.HomeListFilterBean
import com.fallllllll.lipperwithkotlin.utils.getActivityController
import com.fallllllll.lipperwithkotlin.utils.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.fakes.RoboMenuItem

/**
 * Created by fallllllll on 2017/6/22/022.
 * GitHub :  https://github.com/348476129/Lipper
 */
@RunWith(MyRobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class ShotsActivityTest {

    val controller = getActivityController<ShotsActivity>()
    val shotsActivity: ShotsActivity by lazy {
        controller.get()
    }
    val presenter = mock<ShotsActivityContract.ShotsActivityPresenter>()
    @Before
    fun beforeTest() {
        shotsActivity.setPresenter(presenter)
        controller.create()
        verify(presenter).attach()
    }

    @Test
    fun testShowBottomSheet() {
        val roboMenuItem = RoboMenuItem(R.id.filter_list)
        shotsActivity.onOptionsItemSelected(roboMenuItem)
        verify(presenter).showBottomSheet()

        shotsActivity.showBottomSheet(HomeListFilterBean("", "", ""))
        assertTrue(shotsActivity.homeBottomSheetFragment?.isAdded ?: false)
    }

    @Test
    fun testShowPopWindow() {
        val layoutMenu = RoboMenuItem(R.id.shots_menu_layout)
        shotsActivity.onOptionsItemSelected(layoutMenu)
        assertTrue(shotsActivity.popWindow.isShowing)
    }

}