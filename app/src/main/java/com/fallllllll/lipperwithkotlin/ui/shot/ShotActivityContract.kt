package com.fallllllll.lipperwithkotlin.ui.shot

import com.fallllllll.lipperwithkotlin.core.presenter.Contract
import com.fallllllll.lipperwithkotlin.data.databean.ShotBean
import java.io.Serializable

/**
 * Created by 康颢曦 on 2017/10/24.
 */
class ShotActivityContract{
        interface ShotActivityView:Contract.BaseView{
            fun showUI(shotBean: ShotBean)
            fun getSerializationArgument(key:String): Serializable
            fun displayAnimationImage(imageUrl :String)
            fun showLoadShotView()
            fun getShotFail(s:String)
        }
    interface ShotActivityPresenter:Contract.Presenter
}