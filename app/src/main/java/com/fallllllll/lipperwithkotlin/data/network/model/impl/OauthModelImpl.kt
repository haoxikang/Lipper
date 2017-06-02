package com.fallllllll.lipperwithkotlin.data.network.model.impl

import com.fallllllll.lipperwithkotlin.core.constants.CALLBACK_URL
import com.fallllllll.lipperwithkotlin.core.constants.CLIENT_ID
import com.fallllllll.lipperwithkotlin.core.constants.CLIENT_SECRET
import com.fallllllll.lipperwithkotlin.core.constants.PAGE_COUNT
import com.fallllllll.lipperwithkotlin.data.local.user.UserToken
import com.fallllllll.lipperwithkotlin.data.network.DribbbleHttpMethods
import com.fallllllll.lipperwithkotlin.data.network.OauthHttpMethods
import com.fallllllll.lipperwithkotlin.data.network.model.DribbbleModel
import com.fallllllll.lipperwithkotlin.data.network.model.OauthModel
import com.fallllllll.lipperwithkotlin.data.network.service.DribbbleService
import com.fallllllll.lipperwithkotlin.data.network.service.OauthService
import io.reactivex.Flowable
import okhttp3.MediaType
import okhttp3.RequestBody

/**
 * Created by fallllllll on 2017/6/2/002.
 * GitHub :  https://github.com/348476129/Lipper
 */
class OauthModelImpl private constructor(val oauthService: OauthService) : OauthModel {

    companion object {
        fun getInstance(): OauthModelImpl = Inner.model
    }

    private object Inner {
        val model = OauthModelImpl(OauthHttpMethods().service)
    }

    override fun getToken(code: String): Flowable<UserToken> {
        val client_id = RequestBody.create(MediaType.parse("multipart/form-data"), CLIENT_ID)
        val client_secret = RequestBody.create(MediaType.parse("multipart/form-data"), CLIENT_SECRET)
        val redirect_uri = RequestBody.create(MediaType.parse("multipart/form-data"), CALLBACK_URL)
        val oauthCode = RequestBody.create(MediaType.parse("multipart/form-data"), code)
        return oauthService.getToken(client_id, client_secret, redirect_uri, oauthCode)
    }

}