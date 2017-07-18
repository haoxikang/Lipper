package com.fallllllll.lipperwithkotlin.ui.main.home

import com.fallllllll.lipperwithkotlin.core.presenter.Contract
import com.fallllllll.lipperwithkotlin.data.databean.HomeListFilterBean
import com.fallllllll.lipperwithkotlin.data.local.user.LipperUser
import com.fallllllll.lipperwithkotlin.utils.LoginTransitionBundle

/**
 * Created by 康颢曦 on 2017/6/18.
 */
open class ShotsActivityContract {
     interface ShotsActivityView {

        fun showBottomSheet(homeListFilterBean: HomeListFilterBean)

         fun showUserUI(lipperUser: LipperUser)

         fun LogOut()
         fun showMenuLoginAnimation()
         fun showUserImageLoginAnimation()
         fun goUserActivity()
         fun goUserCenterActivity()
     }

     interface ShotsActivityPresenter : Contract.Presenter {
        fun showBottomSheet()
         fun menuActivityClick()
         fun userImageClick()

    }
}