package com.fallllllll.lipperwithkotlin.ui.shoslist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.fall.generalrecyclerviewfragment.GeneralAdapter
import com.fall.generalrecyclerviewfragment.GeneralDataController
import com.fallllllll.lipperwithkotlin.R
import com.fallllllll.lipperwithkotlin.core.constants.USER_IMAGE_SIZE
import com.fallllllll.lipperwithkotlin.core.expandFunction.dpTopx
import com.fallllllll.lipperwithkotlin.core.expandFunction.numberToK
import com.fallllllll.lipperwithkotlin.data.databean.ShotBean
import com.fallllllll.lipperwithkotlin.utils.changeLikeStatus
import com.fallllllll.lipperwithkotlin.utils.cleanLikesStatus
import com.fallllllll.lipperwithkotlin.utils.getTime
import kotlinx.android.synthetic.main.item_view_shots.view.*

/**
 * Created by fall on 2017/6/19/019.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
class ShotsListAdapter : RecyclerView.Adapter<ShotsListAdapter.ShotsListViewHolder>(), GeneralAdapter {
    private lateinit var context: android.content.Context
    var itemClick: (View, ShotBean) -> Unit = { _, _ -> }
    var favoriteClick: (View, Int, ShotBean) -> Unit = { _, _, _ -> }
    private val stringGeneralDataController: GeneralDataController<ShotBean> by lazy {
        GeneralDataController<ShotBean>(this)
    }

    override fun onBindViewHolder(holder: ShotsListAdapter.ShotsListViewHolder, position: Int) {
        val shotBean = stringGeneralDataController.datas[position]
        holder.bindView(position, shotBean)
    }

    override fun getItemCount(): Int = stringGeneralDataController.datas.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShotsListAdapter.ShotsListViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_view_shots, parent, false)
        return ShotsListViewHolder(view, itemClick, favoriteClick)
    }

    override fun getGeneralDataController() = stringGeneralDataController

    inner class ShotsListViewHolder(view: View, private val itemClick: (View, ShotBean) -> Unit, private val favoriteClick: (View, Int, ShotBean) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bindView(position: Int, shotBean: ShotBean) {
            with(shotBean) {
                with(itemView) {
                    val layoutParams = itemShotCardView.layoutParams as LinearLayout.LayoutParams
                    if (position == 0) {
                        layoutParams.topMargin = context.dpTopx(10).toInt()
                    } else {
                        layoutParams.topMargin = context.dpTopx(0).toInt()
                    }
                    itemShotCardView.requestLayout()
                    itemShotCardView.setOnClickListener { itemClick(shotImage, shotBean) }
                    userImage.setOnClickListener { }
                    userImage.loadImage(USER_IMAGE_SIZE, USER_IMAGE_SIZE, user?.avatarUrl ?: "")


                    if (!images?.hidpi.isNullOrEmpty() && !animated) {
                        shotImage.loadImage(url = images?.hidpi ?: "")
                    } else {
                        shotImage.loadImage(url = images?.normal ?: "")
                    }
                    userName.text = user?.name
                    shotTime.text = "${getTime(updatedAt)}"
                    if (tags == null || tags.isEmpty()) {
                        tagText.text = (context.getString(R.string.no_tags))
                    } else {
                        var s = ""
                        tags.forEach { s = s + it + "    " }
                        tagText.text = (s)
                    }
                    commentText.text = shotBean.commentsCount?.numberToK()
                    viewsText.text = shotBean.viewsCount?.numberToK()
                    likeText.text = shotBean.likesCount.numberToK()
                    if (shotBean.isLike) {
                        likeImage.setImageResource(R.drawable.ic_favorite_pink)
                    } else {
                        likeImage.setImageResource(R.drawable.ic_favorite_grey)
                    }
                    likeLayout.setOnClickListener { favoriteClick(likeImage, position, shotBean) }

                    if (shotBean.animated) {
                        gitView.visibility = View.VISIBLE
                    } else {
                        gitView.visibility = View.GONE

                    }

                }
            }


        }
    }

    fun updateLikeState() {
        stringGeneralDataController.datas.changeLikeStatus()
        notifyDataSetChanged()
    }

    fun cleanLikeState() {
        stringGeneralDataController.datas.cleanLikesStatus()
        notifyDataSetChanged()
    }
}