package com.fallllllll.lipperwithkotlin.ui.main.homelist

import com.fallllllll.lipperwithkotlin.core.dagger.AppComponent
import com.fallllllll.lipperwithkotlin.ui.shoslist.ShotsListFragment
import dagger.Component

/**
 * Created by fall on 2017/6/19/019.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(HomeListModule::class))
interface HomeListComponent {
    fun inject(shotsListFragment: ShotsListFragment)
}