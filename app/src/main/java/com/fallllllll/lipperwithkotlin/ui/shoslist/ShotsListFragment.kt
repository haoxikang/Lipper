package com.fallllllll.lipperwithkotlin.ui.shoslist

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.fall.generalrecyclerviewfragment.GeneralContract
import com.fallllllll.AppApplication
import com.fallllllll.lipperwithkotlin.core.expandFunction.getNavigationBarHeight
import com.fallllllll.lipperwithkotlin.core.fragment.BaseListFragment
import com.fallllllll.lipperwithkotlin.data.databean.UserLikesBean
import com.fallllllll.lipperwithkotlin.general_presenter.LikeAndUnlikePresenter.LikeAndUnlikeContract
import com.fallllllll.lipperwithkotlin.general_presenter.LikeAndUnlikePresenter.LikeAndUnlikeModule
import com.fallllllll.lipperwithkotlin.general_presenter.shot_likes_presenter.ShotLikesContract
import com.fallllllll.lipperwithkotlin.general_presenter.shot_likes_presenter.ShotLikesModule
import com.fallllllll.lipperwithkotlin.ui.main.homelist.DaggerHomeListComponent
import com.fallllllll.lipperwithkotlin.ui.main.homelist.HomeListModule
import com.fallllllll.lipperwithkotlin.ui.search.DaggerSearchListComponent
import com.fallllllll.lipperwithkotlin.ui.search.SearchListModule
import javax.inject.Inject

/**
 * Created by fall on 2017/6/19/019.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
const val KEY_TYPE = "ShotsListFragment.keytype"
const val KEY_WORD = "ShotsListFragment.keyword"
const val HOME_TYPE = 1
const val SEARCH_TYPE = 0

class ShotsListFragment : BaseListFragment(), ShotsListContract.ShotsListView, LikeAndUnlikeContract.LikeAndUnlikeView, ShotLikesContract.ShotLikesView {


    private val type by lazy {
        arguments.getInt(KEY_TYPE, 1)
    }
    private val word: String by lazy {
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

    @Inject
    lateinit var likeAndUnlikePresenter: LikeAndUnlikeContract.LikeAndUnlikePresenter

    @Inject
    lateinit var shotLikesPresenter: ShotLikesContract.ShotLikesPresenter

    private val shotsListAdapter by lazy {
        ShotsListAdapter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (type == SEARCH_TYPE) {
            DaggerSearchListComponent
                    .builder()
                    .appComponent(AppApplication.instance.appComponent)
                    .searchListModule(SearchListModule(this, word))
                    .likeAndUnlikeModule(LikeAndUnlikeModule(this))
                    .shotLikesModule(ShotLikesModule(this))
                    .build()
                    .inject(this)
        } else if (type == HOME_TYPE) {
            DaggerHomeListComponent
                    .builder()
                    .appComponent(AppApplication.instance.appComponent)
                    .homeListModule(HomeListModule(this))
                    .likeAndUnlikeModule(LikeAndUnlikeModule(this))
                    .shotLikesModule(ShotLikesModule(this))
                    .build()
                    .inject(this)


        }
        presenterLifecycleHelper.addPresenter(shotsListPresenter)
        presenterLifecycleHelper.addPresenter(likeAndUnlikePresenter)
        presenterLifecycleHelper.addPresenter(shotLikesPresenter)
        shotLikesPresenter.onPresenterCreate()

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


    override fun setErrorViewVisible(isShow: Boolean) {
        if (isShow) {
            errorLayout.visibility = View.VISIBLE
        } else {
            errorLayout.visibility = View.GONE
        }
    }



    override fun like(position: Int) {
        shotsListAdapter.notifyItemChanged(position)
    }

    override fun getShotLikesFail() {

    }

    override fun getShotLikesSuccess(shots: List<UserLikesBean>) {
        if (recyclerView.adapter != null) {
            shotsListAdapter.updateLikeState()
        }
    }

    override fun cleanLikes() {
        if (recyclerView.adapter != null) {
            shotsListAdapter.cleanLikeState()
        }
    }

    override fun initListeners() {
        super.initListeners()
        shotsListAdapter.itemClick = {
            showToast("点击了${it.id}")
        }
        shotsListAdapter.favoriteClick = { position, shotBean ->
            likeAndUnlikePresenter.likeShot(shotBean,position)
        }
    }
}