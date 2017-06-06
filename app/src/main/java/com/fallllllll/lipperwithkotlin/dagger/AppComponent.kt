package com.fallllllll.lipperwithkotlin.dagger

import android.content.Context
import com.fallllllll.lipperwithkotlin.core.dagger.AppModule
import com.fallllllll.lipperwithkotlin.data.network.model.DribbbleModel
import com.fallllllll.lipperwithkotlin.data.network.model.OauthModel
import dagger.Component

/**
 * Created by fallllllll on 2017/6/6/006.
 * GitHub :  https://github.com/348476129/Lipper
 */
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun dribbbleModel(): DribbbleModel
    fun oauthModel(): OauthModel
    fun context(): Context
}