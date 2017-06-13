package com.fallllllll

import android.app.Application
import com.fallllllll.lipperwithkotlin.core.dagger.AppComponent
import com.fallllllll.lipperwithkotlin.core.dagger.AppModule
import com.fallllllll.lipperwithkotlin.core.dagger.DaggerAppComponent
import com.google.gson.Gson

/**
 * Created by fallllllll on 2017/6/1/001.
 * GitHub :  https://github.com/348476129/Lipper
 */
class AppApplication :Application(){

     val appComponent: AppComponent by lazy {
         DaggerAppComponent.builder()
                 .appModule(AppModule(this))
                 .build()
     }

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