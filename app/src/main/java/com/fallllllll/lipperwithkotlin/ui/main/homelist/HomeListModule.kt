package com.fallllllll.lipperwithkotlin.ui.main.homelist

import com.fallllllll.lipperwithkotlin.data.network.model.DribbbleModel
import com.fallllllll.lipperwithkotlin.ui.shoslist.ShotsListContract
import dagger.Module
import dagger.Provides

/**
 * Created by fallllllll on 2017/6/19/019.
 * GitHub :  https://github.com/348476129/Lipper
 */
@Module
class HomeListModule(val shotsListView: ShotsListContract.ShotsListView) {
    @Provides
    fun provideShotsListPresenter(dribbbleModel: DribbbleModel): ShotsListContract.ShotsListPresenter = HomeListPresenterImpl(dribbbleModel, shotsListView)
}