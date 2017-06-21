package com.fallllllll.lipperwithkotlin.utils

import android.app.Activity
import org.mockito.Mockito
import org.robolectric.Robolectric
import org.robolectric.Robolectric.buildActivity
import org.robolectric.android.controller.ActivityController

/**
 * Created by fallllllll on 2017/6/20/020.
 * GitHub :  https://github.com/348476129/Lipper
 */
inline  fun<reified T:Any> mock():T = Mockito.mock(T::class.java)

inline fun <reified T:Activity>  getActivityController():ActivityController<T >{
  return  buildActivity(T::class.java)
}