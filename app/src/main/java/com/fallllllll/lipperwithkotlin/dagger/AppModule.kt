package com.fallllllll.lipperwithkotlin.dagger

import android.content.Context
import com.fallllllll.lipperwithkotlin.data.network.model.impl.DribbbleModelImpl
import com.fallllllll.lipperwithkotlin.data.network.model.impl.OauthModelImpl
import dagger.Module
import dagger.Provides

/**
 * Created by fallllllll on 2017/6/6/006.
 * GitHub :  https://github.com/348476129/Lipper
 */
@Module
class AppModule(private val context: Context) {
    @Provides
    fun provideDribbbleModel() = DribbbleModelImpl.getInstance()

    @Provides
    fun provideOauthModel() = OauthModelImpl.getInstance()

    @Provides
    fun provideContext() = context
}