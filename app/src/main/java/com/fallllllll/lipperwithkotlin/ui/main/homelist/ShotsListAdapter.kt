package com.fallllllll.lipperwithkotlin.ui.main.homelist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.fall.generalrecyclerviewfragment.GeneralAdapter
import com.fall.generalrecyclerviewfragment.GeneralDataController
import com.fallllllll.lipperwithkotlin.R
import com.fallllllll.lipperwithkotlin.core.constants.SHOTS_LAYOUT_LARGE
import com.fallllllll.lipperwithkotlin.core.constants.SHOTS_LAYOUT_ONLY_IMAGE
import com.fallllllll.lipperwithkotlin.core.constants.USER_IMAGE_SIZE
import com.fallllllll.lipperwithkotlin.core.expandFunction.numberToK
import com.fallllllll.lipperwithkotlin.core.expandFunction.showImage
import com.fallllllll.lipperwithkotlin.data.databean.ShotBean
import kotlinx.android.synthetic.main.item_view_shots.view.*

/**
 * Created by fallllllll on 2017/6/19/019.
 * GitHub :  https://github.com/348476129/Lipper
 */
class ShotsListAdapter(var shotsListLayoutType: String) : RecyclerView.Adapter<ShotsListAdapter.ShotsListViewHolder>(), GeneralAdapter {
    private lateinit var context: Context
    var itemClick: (ShotBean) -> Unit = {}
    private val stringGeneralDataController: GeneralDataController<ShotBean> by lazy {
        GeneralDataController<ShotBean>(this)
    }

    override fun onBindViewHolder(holder: ShotsListViewHolder, position: Int) {
        val shotBean = stringGeneralDataController.datas.get(position)
        holder.bindView(shotBean)
    }

    override fun getItemCount(): Int = stringGeneralDataController.datas.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShotsListViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_view_shots, parent, false)
        return ShotsListViewHolder(view, itemClick)
    }

    override fun getGeneralDataController() = stringGeneralDataController

    inner class ShotsListViewHolder(view: View, val itemClick: (ShotBean) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bindView(shotBean: ShotBean) {
            itemView.itemShotUserImage.showImage(USER_IMAGE_SIZE, USER_IMAGE_SIZE, shotBean.user?.avatarUrl ?: "")
            itemView.itemShotUserName.text = shotBean.user?.name
            if (shotBean.reboundSourceUrl.isNullOrEmpty()) {
                itemView.itemShotReboundImage.visibility = GONE
            } else {
                itemView.itemShotReboundImage.visibility = VISIBLE
            }
            itemView.itemShotCommentsNum.text = shotBean.commentsCount?.numberToK()
            itemView.itemShotViewsCount.text = shotBean.viewsCount?.numberToK()
            itemView.itemShotLikeCount.text = shotBean.likesCount?.numberToK()

            if (shotsListLayoutType == SHOTS_LAYOUT_LARGE) {
                itemView.itemShotLikeCount.setPadding(context.resources.getDimension(R.dimen.shots_item_padding_left_large).toInt(), 0, 0, 0)
                itemView.itemShotReplay.setPadding(context.resources.getDimension(R.dimen.shots_item_padding_left_large).toInt(), 0, 0, 0)
                itemView.itemShotCommentsLayout.visibility = View.VISIBLE
                itemView.itemShotViewsText.visibility = View.VISIBLE
                itemView.itemShotLikeText.visibility = View.VISIBLE
                itemView.itemShotRootLayout.setPadding(0, context.resources.getDimension(R.dimen.shots_padding_top_large).toInt(), 0, 0)
            } else {
                itemView.itemShotLikeCount.setPadding(context.resources.getDimension(R.dimen.shots_item_padding_left_small).toInt(), 0, 0, 0)
                itemView.itemShotReplay.setPadding(context.resources.getDimension(R.dimen.shots_item_padding_left_small).toInt(), 0, 0, 0)
                itemView.itemShotRootLayout.setPadding(0, context.resources.getDimension(R.dimen.shots_padding_top_small).toInt(), 0, 0)
                itemView.itemShotCommentsLayout.visibility = View.GONE
                itemView.itemShotViewsText.visibility = View.GONE
                itemView.itemShotLikeText.visibility = View.GONE
            }
            if (shotsListLayoutType == SHOTS_LAYOUT_ONLY_IMAGE) {
                itemView.itemShotTopLayout.visibility = View.GONE
                itemView.itemShotBottomLayout.visibility = View.GONE
                itemView.itemShotCardView.cardElevation = context.resources.getDimension(R.dimen.shots_card_elevation_image)
            } else {
                itemView.itemShotCardView.cardElevation = context.resources.getDimension(R.dimen.shots_card_elevation)
                itemView.itemShotTopLayout.visibility = View.VISIBLE
                itemView.itemShotBottomLayout.visibility = View.VISIBLE
            }
            itemView.itemShotImage.setOnClickListener { itemClick.invoke(shotBean) }
        }
    }
}