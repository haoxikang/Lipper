package com.fallllllll.lipperwithkotlin.ui.main.home

import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.fallllllll.lipperwithkotlin.R
import com.fallllllll.lipperwithkotlin.core.constants.*
import com.fallllllll.lipperwithkotlin.core.rxjava.RxBus
import com.fallllllll.lipperwithkotlin.data.databean.eventBean.ShotsListFilterEvent
import kotlinx.android.synthetic.main.fragment_home_bottom_sheet.*

/**
 * Created by 康颢曦 on 2017/6/14.
 */


const val TIME_KEY = "HomeBottomSheetFragment.time"
const val TYPE_KEY = "HomeBottomSheetFragment.type"
const val SORT_KEY = "HomeBottomSheetFragment.sort"

class HomeBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var time: String
    private lateinit var type: String
    private lateinit var sort: String
    lateinit var radioStatus: HomeBottomSheetFragmentStatus

    companion object {
        fun newInstance(time: String, type: String, sort: String): HomeBottomSheetFragment {
            val args = Bundle()
            args.putString(TIME_KEY, time)
            args.putString(TYPE_KEY, type)
            args.putString(SORT_KEY, sort)
            val fragment = HomeBottomSheetFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home_bottom_sheet, container, false)
        initData()
        initView()
        initListener()
        return view
    }

    private fun initData() {
        time = arguments.getString(TIME_KEY)
        type = arguments.getString(TYPE_KEY)
        sort = arguments.getString(SORT_KEY)
        radioStatus = HomeBottomSheetFragmentStatus(context, time, sort, type)
    }

    private fun initView() {

        for (i in 0..timeGroup.childCount) {
            val timeRadio = timeGroup.getChildAt(i) as RadioButton
            timeRadio.text = radioStatus.listTime[0]
            timeRadio.isChecked = (timeRadio.tag == radioStatus.time)
        }

        for (i in 0..sortGroup.childCount) {
            val sortRadio = sortGroup.getChildAt(i) as RadioButton
            sortRadio.text = radioStatus.listTime[0]
            sortRadio.isChecked = (sortRadio.tag == radioStatus.sort)
        }

        for (i in 0..typeGroup.childCount) {
            val typeRadio = typeGroup.getChildAt(i) as RadioButton
            typeRadio.text = radioStatus.listTime[0]
            typeRadio.isChecked = (typeRadio.tag == radioStatus.type)
        }

    }

    private fun initListener() {
        timeGroup.setOnCheckedChangeListener({ group, checkedId -> time = group.findViewById(checkedId).tag as String })
        typeGroup.setOnCheckedChangeListener({ group, checkedId -> type = group.findViewById(checkedId).tag as String })
        sortGroup.setOnCheckedChangeListener({ group, checkedId -> sort = group.findViewById(checkedId).tag as String })
    }

    override fun onDismiss(dialog: DialogInterface?) {
        RxBus.get().post(ShotsListFilterEvent(time, sort, type))
        super.onDismiss(dialog)
    }

}

