package com.fallllllll

import android.app.Application
import com.google.gson.Gson

/**
 * Created by fallllllll on 2017/6/1/001.
 * GitHub :  https://github.com/348476129/Lipper
 */
class AppApplication :Application(){

    companion object{
        lateinit var instance:AppApplication
    }

   lateinit var  gson:Gson
    override fun onCreate() {
        super.onCreate()
        instance=this
        gson=Gson()
    }
}