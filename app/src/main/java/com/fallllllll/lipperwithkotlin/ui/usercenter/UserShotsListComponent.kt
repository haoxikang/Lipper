package com.fallllllll.lipperwithkotlin.ui.usercenter

import com.fallllllll.lipperwithkotlin.core.dagger.AppComponent
import dagger.Component

/**
 * Created by qqq34 on 2017/10/17.
 */
@Component(dependencies = arrayOf(AppComponent::class),modules = arrayOf(UserShotsListModule::class))
interface UserShotsListComponent {
    fun inject(userCenterShotsListFragment: UserCenterShotsListFragment)
}
