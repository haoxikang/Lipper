package com.fallllllll.lipperwithkotlin

import org.apache.tools.ant.Main
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config

/**
 * Created by fallllllll on 2017/5/26/026.
 * GitHub :  https://github.com/348476129/Lipper
 */
@RunWith(MyRobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(23))
class MyTest {
    @Test
    fun testDemo() {
        val activityController = Robolectric.buildActivity(MainActivity::class.java)
    }
}
