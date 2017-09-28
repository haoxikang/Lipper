package com.fallllllll.lipperwithkotlin.data.local.user

import com.fallllllll.lipperwithkotlin.core.constants.*
import com.fallllllll.lipperwithkotlin.core.rxjava.RxBus
import com.fallllllll.lipperwithkotlin.data.databean.ShotBean
import com.fallllllll.lipperwithkotlin.data.databean.UserLikesBean
import com.fallllllll.lipperwithkotlin.data.databean.eventBean.LoginEvent
import com.fallllllll.lipperwithkotlin.data.local.datatank.DelegatesExt
import com.fallllllll.lipperwithkotlin.data.local.datatank.ObjectTanker
import com.google.gson.reflect.TypeToken

/**
 * Created by fall on 2017/6/2/002.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
class UserManager private constructor() {


    private val lipperUserTanker by lazy { ObjectTanker<LipperUser>(KEY_LIPPER_USER) }
    private val userLikeTanker by lazy { ObjectTanker<List<UserLikesBean>>(KEY_USER_LIKES) }

    var access_token: String by DelegatesExt.valuePreference(KEY_USER_TOKEN, "")
    var token_type: String by DelegatesExt.valuePreference(KEY_TOKEN_TYPE, "")
    var scope: String by DelegatesExt.valuePreference(KEY_TOKEN_SCOPE, "")
    var created_at: Int by DelegatesExt.valuePreference(KEY_CREATED_AT, -1)

    var lipperUser = lipperUserTanker.create(LipperUser::class.java)

    var shotBeanList = userLikeTanker.create(object : TypeToken<List<UserLikesBean>>() {}.type)


    fun updateUser(lipperUser: LipperUser) {
        this.lipperUser = lipperUser
        lipperUserTanker.update(lipperUser)

    }

    fun updateUserLike(shots: List<UserLikesBean>) {
        this.shotBeanList = shots
        userLikeTanker.update(shots)
    }


    fun updateToken(userToken: UserToken) {
        with(userToken) {
            this@UserManager.access_token = access_token ?: ""
            this@UserManager.scope = scope ?: ""
            this@UserManager.created_at = created_at ?: -1
            this@UserManager.token_type = token_type ?: ""
        }
    }

    fun isLogin() = !access_token.isEmpty()

    fun logOut() {
        access_token = ""
        scope = ""
        created_at = -1
        token_type = ""
        userLikeTanker.update(ArrayList())
        lipperUserTanker.update(LipperUser())
        RxBus.get().post(LoginEvent(false))
    }

    companion object {
        fun get(): UserManager = Inner.userManager
    }

    private object Inner {
        val userManager = UserManager()
    }
}
