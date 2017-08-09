package com.fallllllll.lipperwithkotlin.ui.main.home

import android.app.ActivityOptions
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RelativeLayout
import com.fallllllll.lipperwithkotlin.R
import com.fallllllll.lipperwithkotlin.core.activity.BaseActivity
import com.fallllllll.lipperwithkotlin.core.constants.USER_IMAGE_SIZE
import com.fallllllll.lipperwithkotlin.core.expandFunction.getStatusBarHeight
import com.fallllllll.lipperwithkotlin.core.expandFunction.goLogin
import com.fallllllll.lipperwithkotlin.core.expandFunction.setImageTranslucent
import com.fallllllll.lipperwithkotlin.core.expandFunction.showImage
import com.fallllllll.lipperwithkotlin.data.local.user.LipperUser
import com.fallllllll.lipperwithkotlin.ui.search.SearchActivity
import com.fallllllll.lipperwithkotlin.ui.shoslist.HOME_TYPE
import com.fallllllll.lipperwithkotlin.ui.shoslist.ShotsListFragment
import com.fallllllll.lipperwithkotlin.ui.usercenter.UserCenterActivity
import kotlinx.android.synthetic.main.activity_shots.*
import kotlinx.android.synthetic.main.view_drawer_layout.*
import org.jetbrains.anko.startActivity

/**
 * Created by 康颢曦 on 2017/6/18.
 */
class ShotsActivity : BaseActivity(), ShotsActivityContract.ShotsActivityView {


    val listTime: Array<String> by lazy {
        resources.getStringArray(R.array.time)
    }
    val listSort: Array<String> by lazy {
        resources.getStringArray(R.array.sort)
    }
    val listType: Array<String>by lazy {
        resources.getStringArray(R.array.type)
    }

    private val shotsListFragment by lazy {
        ShotsListFragment.newInstance(HOME_TYPE, "")
    }
    private var presenter: ShotsActivityContract.ShotsActivityPresenter? = null

    override fun LogOut() {
        userImage.showImage("", false)
    }

    override fun showUserUI(lipperUser: LipperUser) {
        userImage.showImage(USER_IMAGE_SIZE, USER_IMAGE_SIZE, lipperUser.avatarUrl ?: "")
    }





    override fun initViewAndData() {
        setContentView(R.layout.activity_shots)
        initToolbar()
        initDrawerLayout()
        initTabText()
        setImageTranslucent()
        showFragment()
        initPresenter()
    }

    private fun initPresenter() {
        if (presenter == null) {
            presenter = ShotsActivityPresenterImpl(this)
        }
        presenterLifecycleHelper.addPresenter(presenter!!)
        presenter?.onPresenterCreate()
    }

    private fun initToolbar() {
        shotsToolbar.title = ""
        val layoutParams = shotsToolbar.layoutParams as RelativeLayout.LayoutParams
        layoutParams.topMargin = getStatusBarHeight()
        toolbarLayout.post {
            toolbarLayout.layoutParams.height = toolbarLayout.height + getStatusBarHeight()
        }
        setSupportActionBar(shotsToolbar)
    }

    private fun initDrawerLayout() {
        val drawerToggle = ActionBarDrawerToggle(this, homeDrawerLayout, shotsToolbar, R.string.open_drawer, R.string.close_drawer)
        drawerToggle.syncState()
        homeDrawerLayout.addDrawerListener(drawerToggle)
    }

    override fun initListeners() {
        userImage.setOnClickListener {
            presenter?.userImageClick()
        }
        shotsToolbar.setNavigationOnClickListener {
            homeDrawerLayout.openDrawer(Gravity.START, true)
        }
    }

    private fun initTabText() {
        textSort.text = listSort[0]
        textTime.text = listTime[0]
        textType.text = listType[0]
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.shots_menu_search -> {
                val searchMenuView = shotsToolbar.findViewById<View>(R.id.shots_menu_search)
                val options = ActivityOptions.makeSceneTransitionAnimation(this, searchMenuView, getString(R.string.transition_search_back)).toBundle()
                startActivity(Intent(this, SearchActivity::class.java), options)
            }
//            R.id.shots_menu_activity -> {
//               presenter?.menuActivityClick()
//            }

        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.shots_menu, menu)
        return true
    }

    private fun showFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, shotsListFragment)
        fragmentTransaction.commit()

    }

    override fun showUserImageLoginAnimation() {
        goLogin(ContextCompat.getColor(this, R.color.primary), R.drawable.ic_person_black, userImage)
    }

    override fun showMenuLoginAnimation() {
//        val menuItem  =  shotsToolbar.findViewById<View>(R.id.shots_menu_activity)
//        val login = Intent(this, DribbbleLoginActivity::class.java)
//        MorphTransform.addExtras(login,
//                Color.TRANSPARENT, resources.getDimensionPixelSize(R.dimen.login_background_radius))
//        val options = ActivityOptions.makeSceneTransitionAnimation(this, menuItem, getString(R.string.transition_dribbble_login))
//        startActivity(login, options.toBundle())
    }

    override fun goUserActivity() {

    }

    override fun goUserCenterActivity() {
        startActivity<UserCenterActivity>()
    }


}