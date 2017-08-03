package com.fallllllll.lipperwithkotlin.data.local.user

import com.fallllllll.lipperwithkotlin.BuildConfig
import com.fallllllll.lipperwithkotlin.core.MyRobolectricTestRunner
import com.google.gson.Gson
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Created by qqq34 on 2017/8/3.
 */
@RunWith(MyRobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(23))
class LipperUserFactoryTest {
    @Test
    fun createLipperUser() {
        var lipperUser = LipperUserFactory().createLipperUser()
        assertNotNull(lipperUser)
        assertNull(lipperUser.username)
        lipperUser = Gson().fromJson<LipperUser>("{\"UserID\":\"user\",\"avatar_url\":\"https://cdn.dribbble.com/assets/avatar-default-aa2eab7684294781f93bc99ad394a0eb3249c5768c21390163c9f55ea8ef83a4.gif\",\"bio\":\"\",\"buckets_count\":0,\"buckets_url\":\"https://api.dribbble.com/v1/users/1632887/buckets\",\"can_upload_shot\":false,\"comments_received_count\":0,\"created_at\":\"2017-03-07T08:05:49Z\",\"followers_count\":0,\"followers_url\":\"https://api.dribbble.com/v1/users/1632887/followers\",\"following_url\":\"https://api.dribbble.com/v1/users/1632887/following\",\"followings_count\":0,\"html_url\":\"https://dribbble.com/fallllllll\",\"id\":1632887,\"likes_count\":0,\"likes_received_count\":0,\"likes_url\":\"https://api.dribbble.com/v1/users/1632887/likes\",\"links\":{},\"name\":\"KangHX\",\"pro\":false,\"projects_count\":0,\"rebounds_received_count\":0,\"shots_count\":0,\"shots_url\":\"https://api.dribbble.com/v1/users/1632887/shots\",\"teams_count\":0,\"teams_url\":\"https://api.dribbble.com/v1/users/1632887/teams\",\"type\":\"User\",\"updated_at\":\"2017-03-07T08:06:14Z\",\"username\":\"fallllllll\"}\n", LipperUser::class.java)
        LipperUserFactory().updateLipperUser(lipperUser)
        Thread.sleep(2000)

        assertNotNull(LipperUserFactory().createLipperUser())
        assertNotNull(LipperUserFactory().createLipperUser().username)

    }


}