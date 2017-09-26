package com.fallllllll.lipperwithkotlin.general_presenter.LikeAndUnlikePresenter

import com.fallllllll.lipperwithkotlin.data.network.model.DribbbleModel
import dagger.Module
import dagger.Provides

/**
 * Created by qqq34 on 2017/9/26.
 */
@Module
class LikeAndUnlikeModule(private val view: LikeAndUnlikeContract.LikeAndUnlikeView) {
    @Provides
    fun provideLikeAndUnlikePresenter(dribbbleModel: DribbbleModel):
            LikeAndUnlikeContract.LikeAndUnlikePresenter = LikeAndUnlikePresenterImpl(dribbbleModel, view)
}