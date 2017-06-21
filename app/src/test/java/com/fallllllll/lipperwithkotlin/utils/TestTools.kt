package com.fallllllll.lipperwithkotlin.utils

import com.fallllllll.lipperwithkotlin.data.local.user.LipperUser
import com.fallllllll.lipperwithkotlin.data.local.user.UserManager
import com.fallllllll.lipperwithkotlin.data.local.user.UserToken
import com.google.gson.Gson

/**
 * Created by fallllllll on 2017/6/21/021.
 * GitHub :  https://github.com/348476129/Lipper
 */
fun initUser() {
    val lipperUser = Gson().fromJson<LipperUser>("{\"UserID\":\"user\",\"avatar_url\":\"https://cdn.dribbble.com/assets/avatar-default-aa2eab7684294781f93bc99ad394a0eb3249c5768c21390163c9f55ea8ef83a4.gif\",\"bio\":\"\",\"buckets_count\":0,\"buckets_url\":\"https://api.dribbble.com/v1/users/1632887/buckets\",\"can_upload_shot\":false,\"comments_received_count\":0,\"created_at\":\"2017-03-07T08:05:49Z\",\"followers_count\":0,\"followers_url\":\"https://api.dribbble.com/v1/users/1632887/followers\",\"following_url\":\"https://api.dribbble.com/v1/users/1632887/following\",\"followings_count\":0,\"html_url\":\"https://dribbble.com/fallllllll\",\"id\":1632887,\"likes_count\":0,\"likes_received_count\":0,\"likes_url\":\"https://api.dribbble.com/v1/users/1632887/likes\",\"links\":{},\"name\":\"KangHX\",\"pro\":false,\"projects_count\":0,\"rebounds_received_count\":0,\"shots_count\":0,\"shots_url\":\"https://api.dribbble.com/v1/users/1632887/shots\",\"teams_count\":0,\"teams_url\":\"https://api.dribbble.com/v1/users/1632887/teams\",\"type\":\"User\",\"updated_at\":\"2017-03-07T08:06:14Z\",\"username\":\"fallllllll\"}\n", LipperUser::class.java)
    val userToken = Gson().fromJson<UserToken>("{\"access_token\":\"a9d9a7332cc3454a651bfd3f245e6e6bc04087fcc381b4f469af31f342e9a86f\",\"created_at\":1489635085,\"id\":\"token\",\"scope\":\"public write comment upload\",\"token_type\":\"bearer\"}", UserToken::class.java)
    UserManager.get().updateToken(userToken)
    UserManager.get().updateUser(lipperUser)
}