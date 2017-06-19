package com.fallllllll.lipperwithkotlin.ui.main.homelist

import android.support.v7.widget.RecyclerView
import com.fall.generalrecyclerviewfragment.GeneralContract
import com.fallllllll.lipperwithkotlin.core.fragment.BaseListFragment

/**
 * Created by fallllllll on 2017/6/19/019.
 * GitHub :  https://github.com/348476129/Lipper
 */
class ShotsListFragment:BaseListFragment(),ShotsListContract.ShotsListView {
    override fun getAdapter(): RecyclerView.Adapter<*> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayoutManager(): RecyclerView.LayoutManager {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPresenter(): GeneralContract.Presenter {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun changeRecyclerViewLayout(LayoutType: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun changeItemViewLayout(layoutType: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setErrorViewVisible(isShow: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}