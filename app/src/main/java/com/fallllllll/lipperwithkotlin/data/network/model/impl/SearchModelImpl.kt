package com.fallllllll.lipperwithkotlin.data.network.model.impl
import com.fallllllll.lipperwithkotlin.data.network.model.SearchModel
import com.fallllllll.lipperwithkotlin.data.network.service.SearchService
import com.fallllllll.lipperwithkotlin.data.network.service.SearchServiceHttpMethods

/**
 * Created by fall on 2017/7/5/005.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
class SearchModelImpl private constructor(private  val searchService: SearchService) : SearchModel {

    companion object {
        fun getInstance(): SearchModelImpl = SearchModelImpl(SearchServiceHttpMethods().service)
    }

    override fun search(query: String, sort: String, page: String) = searchService.search(query, sort, page,"identity")

}