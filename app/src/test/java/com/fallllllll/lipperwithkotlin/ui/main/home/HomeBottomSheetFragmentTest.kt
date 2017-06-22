package com.fallllllll.lipperwithkotlin.ui.main.home

import com.fallllllll.lipperwithkotlin.BuildConfig
import com.fallllllll.lipperwithkotlin.core.MyRobolectricTestRunner
import com.fallllllll.lipperwithkotlin.core.constants.DEBUTS
import com.fallllllll.lipperwithkotlin.core.constants.RECENT
import com.fallllllll.lipperwithkotlin.core.constants.WEEK
import com.fallllllll.lipperwithkotlin.core.rxjava.RxBus
import com.fallllllll.lipperwithkotlin.data.databean.eventBean.ShotsListFilterEvent
import com.fallllllll.lipperwithkotlin.utils.RxSchedulersOverrideRule
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_home_bottom_sheet.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment

/**
 * Created by fallllllll on 2017/6/22/022.
 * GitHub :  https://github.com/348476129/Lipper
 */
@RunWith(MyRobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class HomeBottomSheetFragmentTest {
 lateinit   var homeBottomSheetFragment:HomeBottomSheetFragment

    @Rule
    @JvmField
    val mRxSchedulersOverrideRule = RxSchedulersOverrideRule()

    @Before
    fun  beforeTest(){
         homeBottomSheetFragment = HomeBottomSheetFragment.newInstance(WEEK, DEBUTS, RECENT)
        startFragment(homeBottomSheetFragment)
    }

    @Test
    fun testFragmentUI() {

        val radioButtonTime = homeBottomSheetFragment.timeRadio2
        val radioButtonType = homeBottomSheetFragment.typeRadio2
        val radioButtonSort = homeBottomSheetFragment.sortRadio2

        assertTrue(radioButtonTime.isChecked)
        assertTrue(radioButtonType.isChecked)
        assertTrue(radioButtonSort.isChecked)
    }
    @Test
    fun testOnDismiss(){
        RxBus.get().toFlowable<ShotsListFilterEvent>()
                .subscribeBy({
                    assertEquals(it.time, WEEK)
                    assertEquals(it.type, DEBUTS)
                    assertEquals(it.sort, RECENT)
                })
        homeBottomSheetFragment.dismiss()
    }
}