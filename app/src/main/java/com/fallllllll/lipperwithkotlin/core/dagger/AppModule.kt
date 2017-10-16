package com.fallllllll.lipperwithkotlin.core.dagger

import android.content.Context
import com.fallllllll.lipperwithkotlin.data.network.model.DribbbleModel
import com.fallllllll.lipperwithkotlin.data.network.model.OauthModel
import com.fallllllll.lipperwithkotlin.data.network.model.SearchModel
import com.fallllllll.lipperwithkotlin.data.network.model.impl.DribbbleModelImpl
import com.fallllllll.lipperwithkotlin.data.network.model.impl.OauthModelImpl
import com.fallllllll.lipperwithkotlin.data.network.model.impl.SearchModelImpl
import dagger.Module
import dagger.Provides

/**
 * Created by fall on 2017/6/1/001.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
@Module
class AppModule(val context: Context) {

    @Provides
    fun provideDribbbleModel(): DribbbleModel = DribbbleModelImpl.instance

    @Provides
    fun provideOauthModel(): OauthModel = OauthModelImpl.instance

    @Provides
    fun provideSearchModel(): SearchModel = SearchModelImpl.instance

    @Provides
    fun provideContext() = context
}