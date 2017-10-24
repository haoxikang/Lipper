package com.fallllllll.lipperwithkotlin.ui.shot

import com.fallllllll.lipperwithkotlin.data.databean.ShotBean
import com.fallllllll.lipperwithkotlin.data.network.model.DribbbleModel
import dagger.Module
import dagger.Provides

/**
 * Created by 康颢曦 on 2017/10/24.
 */
@Module
class ShotModule(private val shotBean: ShotBean,private val shotActivityView: ShotActivityContract.ShotActivityView) {
    @Provides
    fun provideLoginPresenter(dribbbleModel: DribbbleModel):ShotActivityContract.ShotActivityPresenter=ShotPresenterImpl(shotBean,dribbbleModel,shotActivityView)
}