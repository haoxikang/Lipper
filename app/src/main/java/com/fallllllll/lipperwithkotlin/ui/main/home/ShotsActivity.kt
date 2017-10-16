package com.fallllllll.lipperwithkotlin.ui.main.home

import android.app.ActivityOptions
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.util.Pair
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RelativeLayout
import com.fallllllll.AppApplication
import com.fallllllll.lipperwithkotlin.R
import com.fallllllll.lipperwithkotlin.core.activity.BaseActivity
import com.fallllllll.lipperwithkotlin.core.expandFunction.getStatusBarHeight
import com.fallllllll.lipperwithkotlin.core.expandFunction.goLogin
import com.fallllllll.lipperwithkotlin.core.expandFunction.setImageTranslucent
import com.fallllllll.lipperwithkotlin.data.local.user.LipperUser
import com.fallllllll.lipperwithkotlin.data.local.user.UserManager
import com.fallllllll.lipperwithkotlin.ui.search.SearchActivity
import com.fallllllll.lipperwithkotlin.ui.shoslist.HOME_TYPE
import com.fallllllll.lipperwithkotlin.ui.shoslist.ShotsListFragment
import com.fallllllll.lipperwithkotlin.ui.usercenter.UserCenterActivity
import kotlinx.android.synthetic.main.activity_shots.*
import kotlinx.android.synthetic.main.view_navigation.view.*
import org.jetbrains.anko.intentFor
import javax.inject.Inject


/**
 * Created by fall on 2017/6/18.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
class ShotsActivity : BaseActivity(), ShotsActivityContract.ShotsActivityView {

    private val headerView :View by lazy { navigationView.getHeaderView(0) }

    private val listTime: Array<String> by lazy {
        resources.getStringArray(R.array.time)
    }
    private val listSort: Array<String> by lazy {
        resources.getStringArray(R.array.sort)
    }
    private val listType: Array<String>by lazy {
        resources.getStringArray(R.array.type)
    }

    private val shotsListFragment by lazy {
        ShotsListFragment.newInstance(HOME_TYPE, "")
    }
    @Inject lateinit var presenter: ShotsActivityContract.ShotsActivityPresenter

    override fun showLogoutUI() {
        headerView.userImage.loadImage(url = "")
        headerView.userName.visibility=View.GONE
        headerView.addressText.visibility=View.GONE
        headerView.descriptionText.visibility=View.GONE
        headerView.followLayout.visibility = View.GONE

    }

    override fun showUserUI(lipperUser: LipperUser?) {
        if (lipperUser != null) {
            headerView.userImage.loadImage(url = lipperUser.avatarUrl ?: "")

            if (!lipperUser.username.isNullOrEmpty()){
                headerView.userName.visibility=View.VISIBLE
                headerView.userName.text = lipperUser.username
            }
            if (!lipperUser.location.isNullOrEmpty()){
                headerView.addressText.visibility=View.VISIBLE
                headerView.addressText.text = lipperUser.location
            }
            if (!lipperUser.bio.isNullOrEmpty()){
                headerView.descriptionText.visibility=View.VISIBLE
                headerView.descriptionText.text = lipperUser.bio
            }

            headerView.followLayout.visibility = View.VISIBLE
            headerView.followingCount.text = lipperUser.followingsCount.toString()
            headerView.followerCount.text = lipperUser.followersCount.toString()
        }


    }


    override fun initViewAndData() {
        setContentView(R.layout.activity_shots)
        initToolbar()
        initDrawerLayout()
        setImageTranslucent()
        showFragment()
        initPresenter()
    }

    private fun initPresenter() {
        DaggerShotsActivityComponent.builder()
                .appComponent(AppApplication.instance.appComponent)
                .shotsActivityModule(ShotsActivityModule(this))
                .build()
                .inject(this)
        presenterLifecycleHelper.addPresenter(presenter)
        presenterLifecycleHelper.onPresenterCreate()
    }

    private fun initToolbar() {
        shotsToolbar.title = ""
        val layoutParams = shotsToolbar.layoutParams as RelativeLayout.LayoutParams
        layoutParams.topMargin = getStatusBarHeight()
        toolbarLayout.layoutParams.height = resources.getDimensionPixelSize(R.dimen.home_toolbar_layout) + getStatusBarHeight()
        setSupportActionBar(shotsToolbar)
    }

    private fun initDrawerLayout() {
        val drawerToggle = ActionBarDrawerToggle(this, homeDrawerLayout, shotsToolbar, R.string.open_drawer, R.string.close_drawer)
        drawerToggle.syncState()
        homeDrawerLayout.addDrawerListener(drawerToggle)
        headerView.navigationLayout.post {
            val h = headerView.navigationLayout.height
            headerView.navigationBubble.layoutParams.height=h
            headerView.backView.layoutParams.height=h
        }
    }

    override fun initListeners() {
        headerView.logOut.setOnClickListener {
            if (UserManager.instance.isLogin()) {
                UserManager.instance.logOut()
            }
        }
        textSort.setOnClickListener { presenter.sortTextClick() }
        textTime.setOnClickListener { presenter.timeTextClick() }
        textType.setOnClickListener { presenter.typeTextClick() }
        headerView.userImage.setOnClickListener {
            presenter.userImageClick()
        }
        shotsToolbar.setNavigationOnClickListener {
            homeDrawerLayout.openDrawer(Gravity.START, true)
        }
    }

    override fun showTabText(sort: String, type: String, time: String) {

        val sortMap = HomeFilterMapCreater.getSortMap(this)
        textSort.text = sortMap[sort]

        val timeMap = HomeFilterMapCreater.getTimeMap(this)
        textTime.text = timeMap[time]

        val typeMap = HomeFilterMapCreater.getTypeMap(this)
        textType.text = typeMap[type]
    }

//    private fun getTabName(map: Map<String, String>, value: String): String {
//        map.forEach {
//            if (it.value == value) {
//                return it.key
//            }
//        }
//        return getTabName(map, "")
//    }


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
        goLogin(ContextCompat.getColor(this, R.color.primary), R.drawable.ic_person_black, headerView.userImage)
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
        val intent = intentFor<UserCenterActivity>()
        val userLayoutViewPair = Pair<View,String>(headerView.userInfoLayout,getString(R.string.transition_user_image))
        val userNameViewPair = Pair<View,String>(headerView.userName,getString(R.string.transition_user_name))
        val userLocationViewPair = Pair<View,String>(headerView.addressText,getString(R.string.transition_user_location))
        val userBioViewPair = Pair<View,String>(headerView.descriptionText,getString(R.string.transition_user_bio))
        val userFollowerViewPair = Pair<View,String>(headerView.layoutFollower,getString(R.string.transition_user_follower))
        val userFollowingViewPair = Pair<View,String>(headerView.layoutFollowing,getString(R.string.transition_user_following))
        val transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(this,userLayoutViewPair,userNameViewPair,userLocationViewPair,userBioViewPair,userFollowerViewPair,userFollowingViewPair)
        startActivity(intent, transitionActivityOptions.toBundle())
    }

    override fun showChoiceSortDialog(name: String) {
        val map = HomeFilterMapCreater.getSortMap(this)
        val position = getSelectedPosition(listSort, map, name)
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.dialog_sort_name))
        builder.setSingleChoiceItems(listSort, position, { _, i ->
            val s = listSort[i]
            if (s == textSort.text) return@setSingleChoiceItems
            textSort.text = s
            presenter.changeSort(getKeyFromPosition(listSort, map, i))
            builder.create()
        })
        builder.show()
    }

    override fun showChoiceTypeDialog(name: String) {
        val map = HomeFilterMapCreater.getTypeMap(this)
        val position = getSelectedPosition(listType, map, name)
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.dialog_type_name))
        builder.setSingleChoiceItems(listType, position, { _, i ->
            val s = listType[i]
            if (s == textType.text) return@setSingleChoiceItems
            textType.text = s
            presenter.changeType(getKeyFromPosition(listType, map, i))
        })
        builder.show()
    }

    override fun showChoiceTimeDialog(name: String) {
        val map = HomeFilterMapCreater.getTimeMap(this)
        val position = getSelectedPosition(listTime, map, name)
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.dialog_time_name))
        builder.setSingleChoiceItems(listTime, position, { _, i ->
            val s = listTime[i]
            if (s == textTime.text) return@setSingleChoiceItems
            textTime.text = s
            presenter.changeTime(getKeyFromPosition(listTime, map, i))
        })
        builder.show()
    }

    private fun getKeyFromPosition(valueList: Array<String>, map: Map<String, String>, position: Int): String {
        val s = valueList[position]
        map.forEach {
            if (it.value == s) {
                return it.key
            }
        }
        return ""
    }

    private fun getSelectedPosition(valueList: Array<String>, map: Map<String, String>, name: String): Int {
        val value = map[name]
        (0 until valueList.size)
                .forEach {
                    if (valueList[it] == value) {
                        return it
                    }
                }
        return 0
    }

    override fun onBackPressed() {
        if (homeDrawerLayout.isDrawerOpen(navigationView)){
            homeDrawerLayout.closeDrawers()
            return
        }
        super.onBackPressed()
    }
}