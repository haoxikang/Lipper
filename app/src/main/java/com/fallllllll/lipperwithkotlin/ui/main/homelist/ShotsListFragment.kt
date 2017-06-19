package com.fallllllll.lipperwithkotlin.ui.main.homelist

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.fall.generalrecyclerviewfragment.GeneralContract
import com.fallllllll.AppApplication
import com.fallllllll.lipperwithkotlin.core.constants.SHOTS_LAYOUT_LARGE
import com.fallllllll.lipperwithkotlin.core.constants.SHOTS_LAYOUT_ONLY_IMAGE
import com.fallllllll.lipperwithkotlin.core.constants.SHOTS_LAYOUT_SMALL
import com.fallllllll.lipperwithkotlin.core.expandFunction.getNavigationBarHeight
import com.fallllllll.lipperwithkotlin.core.fragment.BaseListFragment
import javax.inject.Inject

/**
 * Created by fallllllll on 2017/6/19/019.
 * GitHub :  https://github.com/348476129/Lipper
 */
class ShotsListFragment : BaseListFragment(), ShotsListContract.ShotsListView {

    var shotsListModule: ShotsListModule? = null

    @Inject
    lateinit var shotsListPresenter: ShotsListContract.ShotsListPresenter

    private val shotsListAdapter by lazy {
        ShotsListAdapter(SHOTS_LAYOUT_LARGE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerShotsListComponent
                .builder()
                .appComponent(AppApplication.instance.appComponent)
                .shotsListModule(shotsListModule ?: ShotsListModule(this))
                .build()
                .inject(this)
        presenterLifecycleHelper.addPresenter(shotsListPresenter)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.clipToPadding = false
        recyclerView.setPadding(0, 0, 0, activity.getNavigationBarHeight())
    }

    override fun getAdapter(): RecyclerView.Adapter<*> = shotsListAdapter

    override fun getLayoutManager(): RecyclerView.LayoutManager = GridLayoutManager(context, 1)

    override fun getPresenter(): GeneralContract.Presenter = shotsListPresenter as GeneralContract.Presenter

    override fun changeRecyclerViewLayout(layoutType: String) {
        val gridLayoutManager = recyclerView.layoutManager as GridLayoutManager
        when(layoutType){
           SHOTS_LAYOUT_LARGE->{
               shotsListAdapter.shotsListLayoutType = SHOTS_LAYOUT_LARGE
               gridLayoutManager.spanCount=1
           }
            SHOTS_LAYOUT_ONLY_IMAGE->{
                shotsListAdapter.shotsListLayoutType = SHOTS_LAYOUT_ONLY_IMAGE
                gridLayoutManager.spanCount =2
            }
            SHOTS_LAYOUT_SMALL->{
                shotsListAdapter.shotsListLayoutType = SHOTS_LAYOUT_SMALL
                gridLayoutManager.spanCount = 2
            }

        }
        shotsListAdapter.notifyItemRangeChanged(0,shotsListAdapter.itemCount)
    }



    override fun setErrorViewVisible(isShow: Boolean) {
        if (isShow) {
            errorLayout.visibility = View.VISIBLE
        } else {
            errorLayout.visibility = View.GONE
        }
    }
}