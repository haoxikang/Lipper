package com.fallllllll.lipperwithkotlin.ui.search

import com.fallllllll.lipperwithkotlin.core.dagger.AppComponent
import com.fallllllll.lipperwithkotlin.ui.shoslist.ShotsListFragment
import dagger.Component

/**
 * Created by fallllllll on 2017/7/6/006.
 * GitHub :  https://github.com/348476129/Lipper
 */
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(SearchListModule::class))
interface SearchListComponent {
    fun inject(shotsListFragment: ShotsListFragment)
}