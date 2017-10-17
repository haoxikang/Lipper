package com.fallllllll.lipperwithkotlin.ui.usercenter

import com.fall.generalrecyclerviewfragment.GeneralContract
import com.fallllllll.lipperwithkotlin.data.network.model.DribbbleModel
import dagger.Module
import dagger.Provides

/**
 * Created by qqq34 on 2017/10/17.
 */
@Module
class UserShotsListModule {
    @Provides
    fun provideUserShotsPresenter(dribbbleModel: DribbbleModel): GeneralContract.Presenter = UserCenterShotsListPresenter(dribbbleModel)
}