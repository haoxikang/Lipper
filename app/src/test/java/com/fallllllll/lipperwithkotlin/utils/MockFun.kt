package com.fallllllll.lipperwithkotlin.utils

import org.mockito.Mockito

/**
 * Created by fallllllll on 2017/6/20/020.
 * GitHub :  https://github.com/348476129/Lipper
 */
inline  fun<reified T:Any> mock():T = Mockito.mock(T::class.java)