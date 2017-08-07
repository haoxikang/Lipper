package com.fallllllll.lipperwithkotlin.data.network.model.impl

import com.fallllllll.lipperwithkotlin.data.network.SearchServiceMethods
import com.fallllllll.lipperwithkotlin.data.network.model.SearchModel
import com.fallllllll.lipperwithkotlin.data.network.service.SearchService

/**
 * Created by fallllllll on 2017/7/5/005.
 * GitHub :  https://github.com/348476129/Lipper
 */
class SearchModelImpl private constructor(private  val searchService: SearchService) : SearchModel {

    companion object {
        fun getInstance(): SearchModelImpl = SearchModelImpl(SearchServiceMethods().service)
    }

    override fun search(query: String, sort: String, page: String) = searchService.search(query, sort, page,"identity")

}