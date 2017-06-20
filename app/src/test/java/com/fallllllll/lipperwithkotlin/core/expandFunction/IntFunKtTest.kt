package com.fallllllll.lipperwithkotlin.core.expandFunction

import org.junit.Assert.*
import org.junit.Test

/**
 * Created by 康颢曦 on 2017/6/20.
 */
class IntFunKtTest{
    @Test
    fun TestNumberToK(){
        assertEquals("9k",9999.numberToK())
        assertEquals("999",999.numberToK())
        assertEquals("0",0.numberToK())
        assertEquals("1k",1000.numberToK())
    }
}