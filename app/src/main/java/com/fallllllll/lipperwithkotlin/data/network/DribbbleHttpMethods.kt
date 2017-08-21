package com.fallllllll.lipperwithkotlin.data.network

import com.fallllllll.lipperwithkotlin.core.constants.BASE_URL
import com.fallllllll.lipperwithkotlin.data.network.service.DribbbleService

/**
 * Created by fall on 2017/6/2/002.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
class DribbbleHttpMethods : BaseHttpMethods<DribbbleService>() {
    override val baseUrl: String = BASE_URL
    override val serviceClass = DribbbleService::class.java
}