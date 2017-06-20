package com.fallllllll.lipperwithkotlin.core

import org.robolectric.RoboSettings
import org.robolectric.RobolectricTestRunner

/**
 * Created by fallllllll on 2017/6/20/020.
 * GitHub :  https://github.com/348476129/Lipper
 */
class MyRobolectricTestRunner(testClass:Class<Any>) : RobolectricTestRunner(testClass) {
    init {
        RoboSettings.setMavenRepositoryId("alimaven")
        RoboSettings.setMavenRepositoryUrl("http://maven.aliyun.com/nexus/content/groups/public/")
    }
}