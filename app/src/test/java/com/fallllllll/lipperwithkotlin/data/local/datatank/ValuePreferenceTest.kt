package com.fallllllll.lipperwithkotlin.data.local.datatank

import com.fallllllll.lipperwithkotlin.BuildConfig
import com.fallllllll.lipperwithkotlin.core.MyRobolectricTestRunner
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Created by fallllllll on 2017/6/20/020.
 * GitHub :  https://github.com/348476129/Lipper
 */
@RunWith(MyRobolectricTestRunner::class)
@Config(constants = BuildConfig::class,sdk = intArrayOf(23))
class ValuePreferenceTest{


    @Before
    fun beforeTest(){
        var int by DelegatesExt.valuePreference("key_int",1)
        var string by DelegatesExt.valuePreference("key_string","1")
        var long by DelegatesExt.valuePreference("key_long",1L)
        var boolean by DelegatesExt.valuePreference("key_boolean",true)
        var float by DelegatesExt.valuePreference("key_float",1f)
        int = 5
        string = "5"
        long = 5L
        boolean = false
        float = 5f

    }
    @Test
    fun testReadData(){
        val int by DelegatesExt.valuePreference("key_int",1)
        val string by DelegatesExt.valuePreference("key_string","1")
        val long by DelegatesExt.valuePreference("key_long",1L)
        val boolean by DelegatesExt.valuePreference("key_boolean",true)
        val float by DelegatesExt.valuePreference("key_float",1f)
        assertEquals(int,5)
        assertEquals(string,"5")
        assertEquals(long,5L)
        assertEquals(boolean,false)
        assertEquals(float,5f)
    }
}
