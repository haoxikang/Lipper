package com.fallllllll.lipperwithkotlin.ui.main.home

import com.fallllllll.lipperwithkotlin.core.presenter.Contract
import com.fallllllll.lipperwithkotlin.data.local.user.LipperUser

/**
 * Created by fall on 2017/6/18.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
open class ShotsActivityContract {
     interface ShotsActivityView :Contract.BaseView{


         fun showUserUI(lipperUser: LipperUser)

         fun showUserImagePlaceHolder()
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