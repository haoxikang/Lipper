package com.fallllllll.lipperwithkotlin.ui.main.homelist

import com.fallllllll.lipperwithkotlin.core.dagger.AppComponent
import dagger.Component

/**
 * Created by fallllllll on 2017/6/19/019.
 * GitHub :  https://github.com/348476129/Lipper
 */
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ShotsListModule::class))
interface ShotsListComponent {
    fun inject(shotsListFragment: ShotsListFragment)
}