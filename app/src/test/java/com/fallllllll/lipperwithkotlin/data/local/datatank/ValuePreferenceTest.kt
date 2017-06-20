package com.fallllllll.lipperwithkotlin.data.local.datatank

import com.fallllllll.lipperwithkotlin.BuildConfig
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Created by fallllllll on 2017/6/20/020.
 * GitHub :  https://github.com/348476129/Lipper
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class ValuePreferenceTest{


    @Before
    fun text(){
        var a by DelegatesExt.valuePreference("a",1)
        assertEquals(a,1)
        a=5
    }
    @Test
    fun get(){
        var b by DelegatesExt.valuePreference("a",1)
        assertEquals(b,5)
    }
}
