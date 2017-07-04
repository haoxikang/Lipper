package com.fallllllll.lipperwithkotlin.ui.main.home

import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v7.widget.AppCompatRadioButton
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fallllllll.lipperwithkotlin.R
import com.fallllllll.lipperwithkotlin.core.rxjava.RxBus
import com.fallllllll.lipperwithkotlin.data.databean.eventBean.ShotsListFilterEvent
import kotlinx.android.synthetic.main.fragment_home_bottom_sheet.view.*

/**
* Created by 康颢曦 on 2017/6/14.
* GitHub :  https://github.com/348476129/LipperWithKotlin
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
        initView(view)
        initListener(view)
        return view
    }

    private fun initData() {
        time = arguments.getString(TIME_KEY)
        type = arguments.getString(TYPE_KEY)
        sort = arguments.getString(SORT_KEY)
        radioStatus = HomeBottomSheetFragmentStatus(context, time, sort, type)
    }

    private fun initView(view: View) {



        for (i in view.timeGroup.getAllRadioButton(view.timeGroup).indices) {
            val timeRadio = view.timeGroup.getAllRadioButton(view.timeGroup)[i]
            timeRadio.text = radioStatus.listTime?.get(i)
            timeRadio.isChecked = (timeRadio.tag == radioStatus.time)
        }

        for (i in view.sortGroup.getAllRadioButton(view.sortGroup).indices) {
            val sortRadio = view.sortGroup.getAllRadioButton(view.sortGroup)[i]
            sortRadio.text = radioStatus.listSort?.get(i)
            sortRadio.isChecked = (sortRadio.tag == radioStatus.sort)
        }

        for (i in view.typeGroup.getAllRadioButton(view.typeGroup).indices){
            val typeRadio = view.typeGroup.getAllRadioButton(view.typeGroup)[i]
            typeRadio.text = radioStatus.listType?.get(i)
            typeRadio.isChecked = (typeRadio.tag == radioStatus.type)
        }

    }

    private fun initListener(view: View) {
        view.timeGroup.setOnCheckedChangeListener({ group, checkedId -> time = group.findViewById<AppCompatRadioButton>(checkedId).tag as String })
        view.typeGroup.setOnCheckedChangeListener({ group, checkedId -> type = group.findViewById<AppCompatRadioButton>(checkedId).tag as String })
        view.sortGroup.setOnCheckedChangeListener({ group, checkedId -> sort = group.findViewById<AppCompatRadioButton>(checkedId).tag as String })
    }

    override fun onDismiss(dialog: DialogInterface?) {
        RxBus.get().post(ShotsListFilterEvent(time, sort, type))
        super.onDismiss(dialog)
    }

}

