package com.fallllllll.lipperwithkotlin.data.local.user

import com.fallllllll.AppApplication
import com.fallllllll.lipperwithkotlin.core.constants.KEY_LIPPER_USER
import com.fallllllll.lipperwithkotlin.data.local.datatank.DelegatesExt
import org.jetbrains.anko.doAsync

/**
 * Created by fall on 2017/8/3.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
class LipperUserFactory {
  private  val gson by lazy { AppApplication.instance.gson }
   private var userLipperJson: String by DelegatesExt.valuePreference(KEY_LIPPER_USER, "")

    fun createLipperUser(): LipperUser {
        if (!userLipperJson.isEmpty()) {
            return AppApplication.instance.gson.fromJson(userLipperJson, LipperUser::class.java)
        } else {
            return LipperUser()
        }
    }

    fun updateLipperUser(lipperUser: LipperUser) {
        doAsync {
            userLipperJson = gson.toJson(lipperUser)
        }
    }

}