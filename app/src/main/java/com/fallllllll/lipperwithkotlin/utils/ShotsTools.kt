package com.fallllllll.lipperwithkotlin.utils

import com.fallllllll.lipperwithkotlin.data.databean.ShotBean
import com.fallllllll.lipperwithkotlin.data.local.user.UserManager

/**
 * Created by qqq34 on 2017/9/28.
 */
fun List<ShotBean>.changeLikeStatus(): List<ShotBean> {
    if (UserManager.get().isLogin()) {
        val userLikes = UserManager.get().getUserLikes()

        userLikes.forEach { userLikeList ->
            this.forEach {
                if (it.id == userLikeList.shot?.id) {
                    it.isLike = true
                }
            }
        }
    }
    return this
}

fun List<ShotBean>.cleanLikesStatus() {
    this.forEach {
        it.isLike = false
    }
}