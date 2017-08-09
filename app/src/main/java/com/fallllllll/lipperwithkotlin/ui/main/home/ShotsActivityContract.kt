package com.fallllllll.lipperwithkotlin.ui.main.home

import com.fallllllll.lipperwithkotlin.core.presenter.Contract
import com.fallllllll.lipperwithkotlin.data.local.user.LipperUser

/**
 * Created by 康颢曦 on 2017/6/18.
 */
open class ShotsActivityContract {
     interface ShotsActivityView :Contract.BaseView{


         fun showUserUI(lipperUser: LipperUser)

         fun LogOut()
         fun showMenuLoginAnimation()
         fun showUserImageLoginAnimation()
         fun goUserActivity()
         fun goUserCenterActivity()
     }

     interface ShotsActivityPresenter : Contract.Presenter {
         fun menuActivityClick()
         fun userImageClick()

    }
}