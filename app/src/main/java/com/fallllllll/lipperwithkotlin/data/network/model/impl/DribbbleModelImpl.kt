package com.fallllllll.lipperwithkotlin.data.network.model.impl

import com.fallllllll.lipperwithkotlin.core.constants.PAGE_COUNT
import com.fallllllll.lipperwithkotlin.data.network.DribbbleHttpMethods
import com.fallllllll.lipperwithkotlin.data.network.model.DribbbleModel
import com.fallllllll.lipperwithkotlin.data.network.service.DribbbleService

/**
 * Created by fall on 2017/6/2/002.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
class DribbbleModelImpl private constructor(private val dribbbleService: DribbbleService) : DribbbleModel {

    companion object {
        fun getInstance(): DribbbleModelImpl = DribbbleModelImpl(DribbbleHttpMethods().service)
    }

    override fun getShot(list: String, timeframe: String, sort: String, page: String) = dribbbleService.getShot(list, timeframe, sort, page, PAGE_COUNT.toString())

    override fun getUserInfo(token: String) = dribbbleService.getUserInfo("Bearer $token")

}