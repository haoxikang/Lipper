package com.fallllllll.lipperwithkotlin.ui.login

import com.fallllllll.lipperwithkotlin.core.dagger.AppComponent
import dagger.Component

/**
 * Created by fall on 2017/7/3/003.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
@Component(dependencies =arrayOf( AppComponent::class),modules = arrayOf(LoginModule::class))
interface DribbbleLoginComponent {
    fun inject(dribbbleLoginActivity: DribbbleLoginActivity)
}