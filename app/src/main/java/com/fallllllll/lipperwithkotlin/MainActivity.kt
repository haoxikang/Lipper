package com.fallllllll.lipperwithkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.fallllllll.lipperwithkotlin.data.local.datatank.DelegatesExt

/**
 * Created by fallllllll on 2017/5/26/026.
 * GitHub :  https://github.com/348476129/Lipper
 */

class MainActivity : AppCompatActivity() {

    private var  user:User by DelegatesExt.dataClassPreference(this,"user",User(),User::class.java)
    //private var userId:String by DelegatesExt.preference(this,"userId","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      Log.d("aaaa",user.name)
        var user :User = user.copy()
        user.name="aaaaaaa"
        this.user = user
    }

data class User(var name:String="default")

}
