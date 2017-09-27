package com.fallllllll.lipperwithkotlin.general_presenter.shot_likes_presenter

import com.fallllllll.lipperwithkotlin.data.network.model.DribbbleModel
import dagger.Module
import dagger.Provides

/**
 * Created by qqq34 on 2017/9/27.
 */
@Module
class ShotLikesModule(private val view:ShotLikesContract.ShotLikesView){
    @Provides
    fun provideShotLikesPresenter(dribbbleModel: DribbbleModel):
            ShotLikesContract.ShotLikesPresenter = ShotLikesPresenterImpl(dribbbleModel, view)
}