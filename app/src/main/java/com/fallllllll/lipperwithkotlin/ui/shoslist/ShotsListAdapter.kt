package com.fallllllll.lipperwithkotlin.ui.shoslist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.fall.generalrecyclerviewfragment.GeneralAdapter
import com.fall.generalrecyclerviewfragment.GeneralDataController
import com.fallllllll.lipperwithkotlin.R
import com.fallllllll.lipperwithkotlin.core.constants.KEY_LAYOUT_TYPE
import com.fallllllll.lipperwithkotlin.core.constants.SHOTS_LAYOUT_LARGE
import com.fallllllll.lipperwithkotlin.core.constants.SHOTS_LAYOUT_ONLY_IMAGE
import com.fallllllll.lipperwithkotlin.core.constants.USER_IMAGE_SIZE
import com.fallllllll.lipperwithkotlin.core.expandFunction.numberToK
import com.fallllllll.lipperwithkotlin.core.expandFunction.showImage
import com.fallllllll.lipperwithkotlin.data.databean.ShotBean
import com.fallllllll.lipperwithkotlin.data.local.datatank.DelegatesExt
import com.fallllllll.lipperwithkotlin.utils.getTime
import kotlinx.android.synthetic.main.item_view_shots.view.*

/**
 * Created by fallllllll on 2017/6/19/019.
 * GitHub :  https://github.com/348476129/Lipper
 */
class ShotsListAdapter :RecyclerView.Adapter<ShotsListAdapter.ShotsListViewHolder>(), GeneralAdapter {
//    var currentLayoutType: String by  DelegatesExt.valuePreference( KEY_LAYOUT_TYPE, SHOTS_LAYOUT_LARGE)
    private lateinit var context: android.content.Context
    var itemClick: ( ShotBean) -> Unit = {}
    private val stringGeneralDataController: GeneralDataController<ShotBean> by lazy {
       GeneralDataController<ShotBean>(this)
    }

//    fun changeCurrentLayoutType(type: String) {
//        if (currentLayoutType != type) {
//            currentLayoutType = type
//            notifyItemRangeChanged(0,itemCount)
//        }
//    }

    override fun onBindViewHolder(holder:  ShotsListAdapter.ShotsListViewHolder, position: Int) {
        val shotBean = stringGeneralDataController.datas[position]
        holder.bindView(shotBean)
    }

    override fun getItemCount(): Int = stringGeneralDataController.datas.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShotsListAdapter.ShotsListViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate( R.layout.item_view_shots, parent, false)
        return ShotsListViewHolder(view, itemClick)
    }

    override fun getGeneralDataController() = stringGeneralDataController

    inner class ShotsListViewHolder(view : View, val itemClick: (ShotBean) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bindView(shotBean: ShotBean) {
            itemView.userImage.showImage( USER_IMAGE_SIZE,  USER_IMAGE_SIZE, shotBean.user?.avatarUrl ?: "")
            if (!shotBean.images?.hidpi.isNullOrEmpty() && !shotBean.animated) {
                itemView.shotImage.showImage(shotBean.images?.hidpi ?: "", false)
            } else {
                itemView.shotImage.showImage(shotBean.images?.normal ?: "", false)
            }
            itemView.userName.text = shotBean.user?.name
            itemView.shotTime.text ="on  "+ getTime(shotBean.updatedAt?:"")
            if (shotBean.tags==null||shotBean.tags.isEmpty()){
                itemView.tagText.text=(context.getString(R.string.no_tags))
            }else{
                var s = ""
                shotBean.tags.forEach { s=s+it+"    " }
                itemView.tagText.text=(s)
            }
            itemView.commentText.text = shotBean.commentsCount?.numberToK()
            itemView.viewsText.text = shotBean.viewsCount?.numberToK()
            itemView.likeText.text = shotBean.likesCount?.numberToK()
        }
    }
}