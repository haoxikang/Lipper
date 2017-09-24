package com.fallllllll.lipperwithkotlin.data.network

import com.fall.retrofitannotation.RetrofitBuilder
import com.fallllllll.lipperwithkotlin.data.network.converter.DribbbleSearchConverter
import retrofit2.Converter

/**
 * Created by 康颢曦 on 2017/9/24.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
@RetrofitBuilder(arrayOf("com.fallllllll.lipperwithkotlin.data.network.service.SearchService"))
class SearchRetrofitBuilder : DefaultRetrofitBuilder() {
    override fun getConverterFactory(): Converter.Factory {
        return DribbbleSearchConverter.Factory()
    }
}