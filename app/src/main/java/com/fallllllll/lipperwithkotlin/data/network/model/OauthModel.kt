package com.fallllllll.lipperwithkotlin.data.network.model

import com.fallllllll.lipperwithkotlin.data.local.user.UserToken
import io.reactivex.Flowable

/**
 * Created by fall on 2017/6/2/002.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
interface OauthModel {
     fun getToken(code: String): Flowable<UserToken>
}