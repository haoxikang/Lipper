package com.fallllllll.lipperwithkotlin.core.dagger

import android.content.Context
import com.fallllllll.lipperwithkotlin.data.network.model.DribbbleModel
import com.fallllllll.lipperwithkotlin.data.network.model.OauthModel
import com.fallllllll.lipperwithkotlin.data.network.model.SearchModel
import dagger.Component

/**
 * Created by fallllllll on 2017/6/1/001.
 * GitHub :  https://github.com/348476129/Lipper
 */
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun dribbbleModel(): DribbbleModel

    fun oauthModel(): OauthModel

    fun searchModel():SearchModel

    fun context(): Context

}