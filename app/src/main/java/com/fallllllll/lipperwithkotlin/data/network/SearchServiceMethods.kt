package com.fallllllll.lipperwithkotlin.data.network

import com.fallllllll.lipperwithkotlin.core.constants.DRIBBBLE_HOST
import com.fallllllll.lipperwithkotlin.data.network.converter.DribbbleSearchConverter
import com.fallllllll.lipperwithkotlin.data.network.service.SearchService
import retrofit2.Converter

/**
 * Created by fallllllll on 2017/7/5/005.
 * GitHub :  https://github.com/348476129/Lipper
 */
class SearchServiceMethods : BaseHttpMethods<SearchService>() {
    override val baseUrl: String = DRIBBBLE_HOST
    override val serviceClass = SearchService::class.java
    override fun getConverterFactory(): Converter.Factory {
        return DribbbleSearchConverter.Factory()
    }
}