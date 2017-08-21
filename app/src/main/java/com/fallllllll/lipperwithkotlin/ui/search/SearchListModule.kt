package com.fallllllll.lipperwithkotlin.ui.search

import com.fallllllll.lipperwithkotlin.data.network.model.SearchModel
import com.fallllllll.lipperwithkotlin.ui.shoslist.ShotsListContract
import dagger.Module
import dagger.Provides

/**
 * Created by fall on 2017/7/6/006.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
@Module
class SearchListModule(val shotsListView: ShotsListContract.ShotsListView, val keyWord: String) {
    @Provides
    fun provideSearchListPresenter(searchModel: SearchModel): ShotsListContract.ShotsListPresenter = SearchListPresenterImpl(searchModel, shotsListView, keyWord)
}