package com.fallllllll.lipperwithkotlin.ui.main.homelist

import com.fallllllll.lipperwithkotlin.data.network.model.DribbbleModel
import dagger.Module
import dagger.Provides

/**
 * Created by fallllllll on 2017/6/19/019.
 * GitHub :  https://github.com/348476129/Lipper
 */
@Module
class ShotsListModule(val shotsListView: ShotsListContract.ShotsListView) {
    @Provides
    fun provideShotsListPresenter(dribbbleModel: DribbbleModel): ShotsListContract.ShotsListPresenter = ShotsListPresenterImpl(dribbbleModel, shotsListView)
}