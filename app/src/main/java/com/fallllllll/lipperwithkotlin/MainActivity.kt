package com.fallllllll.lipperwithkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fallllllll.lipperwithkotlin.data.local.datatank.DelegatesExt

/**
 * Created by fallllllll on 2017/5/26/026.
 * GitHub :  https://github.com/348476129/Lipper
 */

class MainActivity : AppCompatActivity() {

    private var i by DelegatesExt.valuePreference("bbbbb", "aaa")
    //private var userId:String by DelegatesExt.preference(this,"userId","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    data class User(var name: String = "default")

}
