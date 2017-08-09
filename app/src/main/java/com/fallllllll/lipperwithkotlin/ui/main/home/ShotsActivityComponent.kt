package com.fallllllll.lipperwithkotlin.ui.main.home

import com.fallllllll.lipperwithkotlin.core.dagger.AppComponent
import dagger.Component

/**
 * Created by qqq34 on 2017/8/9.
 */
@Component(dependencies = arrayOf(AppComponent::class),modules = arrayOf(ShotsActivityModule::class))
interface ShotsActivityComponent {
    fun inject(shotsActivity:ShotsActivity)
}