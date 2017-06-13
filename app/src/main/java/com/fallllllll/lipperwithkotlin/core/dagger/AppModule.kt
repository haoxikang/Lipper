package com.fallllllll.lipperwithkotlin.core.dagger

import android.content.Context
import com.fallllllll.lipperwithkotlin.data.network.model.DribbbleModel
import com.fallllllll.lipperwithkotlin.data.network.model.OauthModel
import com.fallllllll.lipperwithkotlin.data.network.model.impl.DribbbleModelImpl
import com.fallllllll.lipperwithkotlin.data.network.model.impl.OauthModelImpl
import dagger.Module
import dagger.Provides

/**
 * Created by fallllllll on 2017/6/1/001.
 * GitHub :  https://github.com/348476129/Lipper
 */
@Module
class AppModule(val context: Context) {

    @Provides
    fun provideDribbbleModel():DribbbleModel = DribbbleModelImpl.getInstance()

    @Provides
    fun provideOauthModel() :OauthModel= OauthModelImpl.getInstance()

    @Provides
    fun provideContext() = context
}