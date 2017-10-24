package com.fallllllll.lipperwithkotlin.ui.shot

import com.fallllllll.lipperwithkotlin.core.dagger.AppComponent
import dagger.Component

/**
 * Created by 康颢曦 on 2017/10/24.
 */
@Component(dependencies = arrayOf(AppComponent::class),modules = arrayOf(ShotModule::class))
interface ShotComponent {
    fun inject(shotActivity: ShotActivity)
}