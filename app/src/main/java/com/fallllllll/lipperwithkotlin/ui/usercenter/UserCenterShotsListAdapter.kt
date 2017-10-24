package com.fallllllll.lipperwithkotlin.ui.usercenter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fall.generalrecyclerviewfragment.GeneralAdapter
import com.fall.generalrecyclerviewfragment.GeneralDataController
import com.fallllllll.lipperwithkotlin.R
import com.fallllllll.lipperwithkotlin.data.databean.ShotBean
import kotlinx.android.synthetic.main.view_shot_only_image.view.*

/**
 * Created by qqq34 on 2017/10/16.
 */
class UserCenterShotsListAdapter : RecyclerView.Adapter<UserCenterShotsListAdapter.UserCenterShotsListViewHolder>(), GeneralAdapter {
    private lateinit var context: android.content.Context
    var itemClick: (ShotBean) -> Unit = {}
    private val dataController: GeneralDataController<ShotBean> by lazy {
        GeneralDataController<ShotBean>(this)
    }

    override fun getGeneralDataController() = dataController

    override fun onBindViewHolder(holder: UserCenterShotsListViewHolder, position: Int) {
        val shotBean = dataController.datas[position]
        holder.bindView(shotBean)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserCenterShotsListViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.view_shot_only_image, parent, false)
        return UserCenterShotsListViewHolder(view, itemClick)
    }

    override fun getItemCount() = dataController.datas.size

    inner class UserCenterShotsListViewHolder(view: View, private val itemClick: (ShotBean) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bindView(shotBean: ShotBean) {
            itemView.onlyImageCardView.setOnClickListener { itemClick.invoke(shotBean) }
            itemView.shotImage.loadWithUrl(url = shotBean.getHDImage())

        }
    }
}