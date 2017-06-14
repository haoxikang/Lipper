package com.fallllllll.lipperwithkotlin.core.presenter

/**
 * Created by fallllllll on 2017/5/27/027.
 * GitHub :  https://github.com/348476129/Lipper
 */
class PresenterLifecycleHelper {
    private val presenterList: ArrayList<Contract.Presenter> = ArrayList()
    fun addPresenter(presenter: Contract.Presenter) {
        presenterList.add(presenter)
        presenter.attach()
    }

    fun onPresenterCreate() {
        presenterList.forEach { it.onPresenterCreate() }
    }

    fun destroyPresenter() {
        presenterList.forEach { it.detach() }
    }
}