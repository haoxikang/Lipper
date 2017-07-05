package com.fallllllll.lipperwithkotlin.core.expandFunction

import com.fallllllll.lipperwithkotlin.BuildConfig
import com.fallllllll.lipperwithkotlin.core.MyRobolectricTestRunner
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Created by 康颢曦 on 2017/6/20.
 */
@RunWith(MyRobolectricTestRunner::class)
@Config(constants = BuildConfig::class,sdk = intArrayOf(23))
class StringFunKtTest {
    @Test
    fun testNumberToK() {
        assertEquals("9k", "9999".numberToK())
        assertEquals("999", "999".numberToK())
        assertEquals("0", "0".numberToK())
        assertEquals("1k", "1000".numberToK())
    }

}