package com.fallllllll.lipperwithkotlin.data.local.user

/**
 * Created by fallllllll on 2017/6/2/002.
 * GitHub :  https://github.com/348476129/Lipper
 */
class UserManager private constructor(val userToken: UserToken) {


    lateinit var lipperUser: LipperUser

    fun updateUser(lipperUser: LipperUser) {
        this.lipperUser = lipperUser
    }

    fun updateToken(userToken: UserToken) {
        with(userToken) {
            this@UserManager.userToken.access_token = access_token
            this@UserManager.userToken.scope = scope
            this@UserManager.userToken.created_at = created_at
            this@UserManager.userToken.token_type = token_type
        }
    }

    fun isLogin() = userToken.access_token != ""

    fun logOut() {
        userToken.access_token = ""
    }

    companion object {
        fun get(): UserManager = Inner.userManager
    }

    private object Inner {
        val userManager = UserManager(UserToken())
    }
}