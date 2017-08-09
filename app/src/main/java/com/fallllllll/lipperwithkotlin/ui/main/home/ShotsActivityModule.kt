package com.fallllllll.lipperwithkotlin.ui.main.home

import com.fallllllll.lipperwithkotlin.data.network.model.DribbbleModel
import dagger.Module
import dagger.Provides

/**
 * Created by qqq34 on 2017/8/9.
 */
@Module
class ShotsActivityModule(val shotsActivityView: ShotsActivityContract.ShotsActivityView) {
    @Provides
    fun provideShotsActivityPresenter(dribbbleModel: DribbbleModel):
            ShotsActivityContract.ShotsActivityPresenter = ShotsActivityPresenterImpl(dribbbleModel,shotsActivityView)
}