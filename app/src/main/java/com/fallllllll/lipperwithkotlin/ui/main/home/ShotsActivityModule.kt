package com.fallllllll.lipperwithkotlin.ui.main.home

import com.fallllllll.lipperwithkotlin.data.network.model.DribbbleModel
import dagger.Module
import dagger.Provides

/**
 * Created by fall on 2017/8/9.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
@Module
class ShotsActivityModule(val shotsActivityView: ShotsActivityContract.ShotsActivityView) {
    @Provides
    fun provideShotsActivityPresenter(dribbbleModel: DribbbleModel):
            ShotsActivityContract.ShotsActivityPresenter = ShotsActivityPresenterImpl(dribbbleModel,shotsActivityView)
}