package com.fallllllll.lipperwithkotlin.core.expandFunction

import org.junit.Assert.*
import org.junit.Test

/**
 * Created by 康颢曦 on 2017/6/20.
 */
class RandomFunKtTest{
    @Test
    fun TestGetRandomString(){

        assertEquals(100, getRandomString(100).length)
        assertEquals(5, getRandomString(5).length)
    }
}