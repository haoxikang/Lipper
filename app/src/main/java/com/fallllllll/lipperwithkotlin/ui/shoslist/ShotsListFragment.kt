package com.fallllllll.lipperwithkotlin.ui.shoslist

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
import com.fallllllll.lipperwithkotlin.ui.main.home.HomeBottomSheetFragment
import com.fallllllll.lipperwithkotlin.ui.main.home.SORT_KEY
import com.fallllllll.lipperwithkotlin.ui.main.home.TIME_KEY
import com.fallllllll.lipperwithkotlin.ui.main.home.TYPE_KEY
import com.fallllllll.lipperwithkotlin.ui.main.homelist.DaggerHomeListComponent
import com.fallllllll.lipperwithkotlin.ui.main.homelist.HomeListModule
import com.fallllllll.lipperwithkotlin.ui.search.DaggerSearchListComponent
import com.fallllllll.lipperwithkotlin.ui.search.SearchListModule
import javax.inject.Inject

/**
 * Created by fallllllll on 2017/6/19/019.
 * GitHub :  https://github.com/348476129/Lipper
 */
const val KEY_TYPE = "ShotsListFragment.keytype"
const val KEY_WORD = "ShotsListFragment.keyword"
const val HOME_TYPE = 1
const val SEARCH_TYPE = 0

class ShotsListFragment : BaseListFragment(), ShotsListContract.ShotsListView {

    var homeListModule: HomeListModule? = null
    var searchListModule: SearchListModule? = null

    val type by lazy {
        arguments.getInt(KEY_TYPE, 1)
    }
    val word: String by lazy {
        arguments.getString(KEY_WORD, "")
    }

    companion object {
        fun newInstance(type: Int, word: String): ShotsListFragment {
            val args = Bundle()
            args.putInt(KEY_TYPE, type)
            args.putString(KEY_WORD, word)
            val fragment = ShotsListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var shotsListPresenter: ShotsListContract.ShotsListPresenter

    private val shotsListAdapter by lazy {
        ShotsListAdapter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (type == SEARCH_TYPE) {
            DaggerSearchListComponent
                    .builder()
                    .appComponent(AppApplication.instance.appComponent)
                    .searchListModule(searchListModule ?: SearchListModule(this, word))
                    .build()
                    .inject(this)
        } else if (type == HOME_TYPE) {
            DaggerHomeListComponent
                    .builder()
                    .appComponent(AppApplication.instance.appComponent)
                    .homeListModule(homeListModule ?: HomeListModule(this))
                    .build()
                    .inject(this)
            presenterLifecycleHelper.addPresenter(shotsListPresenter)
        }

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.clipToPadding = false
        recyclerView.setPadding(0, 0, 0, activity.getNavigationBarHeight())
    }

    override fun getAdapter(): RecyclerView.Adapter<*> = shotsListAdapter

    override fun getLayoutManager(): RecyclerView.LayoutManager {
            return GridLayoutManager(context, 1)


    }

    override fun getPresenter(): GeneralContract.Presenter = shotsListPresenter as GeneralContract.Presenter

//    override fun changeRecyclerViewLayout(layoutType: String) {
//        val gridLayoutManager = recyclerView.layoutManager as GridLayoutManager
//        when (layoutType) {
//            SHOTS_LAYOUT_LARGE -> {
//                gridLayoutManager.spanCount = 1
//                shotsListAdapter.changeCurrentLayoutType(layoutType)
//            }
//            SHOTS_LAYOUT_ONLY_IMAGE -> {
//                gridLayoutManager.spanCount = 2
//                shotsListAdapter.changeCurrentLayoutType(layoutType)
//            }
//            SHOTS_LAYOUT_SMALL -> {
//                gridLayoutManager.spanCount = 2
//                shotsListAdapter.changeCurrentLayoutType(layoutType)
//
//            }
//        }
//
//    }


    override fun setErrorViewVisible(isShow: Boolean) {
        if (isShow) {
            errorLayout.visibility = View.VISIBLE
        } else {
            errorLayout.visibility = View.GONE
        }
    }
}