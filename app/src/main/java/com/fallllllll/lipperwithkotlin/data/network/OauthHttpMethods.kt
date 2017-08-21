package com.fallllllll.lipperwithkotlin.data.network

import com.fallllllll.lipperwithkotlin.core.constants.LOGIN_URL
import com.fallllllll.lipperwithkotlin.data.network.service.OauthService

/**
 * Created by fall on 2017/6/2/002.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
class OauthHttpMethods :BaseHttpMethods<OauthService>(){
    override val baseUrl: String = LOGIN_URL
    override val serviceClass = OauthService::class.java

}