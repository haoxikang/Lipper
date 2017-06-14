package com.fallllllll.lipperwithkotlin.ui.main.home

import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
class HomeBottomSheetFragment :BottomSheetDialogFragment(){
    private lateinit var time :String
    private lateinit var type:String
    private lateinit var sort :String
    lateinit var radioStatus :HomeBottomSheetFragmentStatus
    companion object {
        fun newInstance( time:String,type:String,sort:String):HomeBottomSheetFragment{
            val args = Bundle()
            args.putString(TIME_KEY, time)
            args.putString(TYPE_KEY, type)
            args.putString(SORT_KEY, sort)
            val  fragment =HomeBottomSheetFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home_bottom_sheet,container,false)
        initData()
        initView(view)
        initListener()
        return view
    }
    private fun initData(){
        time = arguments.getString(TIME_KEY)
        type = arguments.getString(TYPE_KEY)
        sort = arguments.getString(SORT_KEY)
        radioStatus = HomeBottomSheetFragmentStatus(context,time,sort,type)
    }
    private fun initView(view: View){
        timeRadio1.text= radioStatus.listTime[0]
        timeRadio1.isChecked = (radioStatus.time== NOW)

        timeRadio2.text= radioStatus.listTime[1]
        timeRadio2.isChecked = (radioStatus.time== WEEK)

        timeRadio3.text= radioStatus.listTime[2]
        timeRadio3.isChecked = (radioStatus.time== MONTH)

        timeRadio4.text= radioStatus.listTime[3]
        timeRadio4.isChecked = (radioStatus.time== YEAR)

        timeRadio5.text= radioStatus.listTime[4]
        timeRadio5.isChecked = (radioStatus.time== EVER)

        sortRadio1.text= radioStatus.listSort[0]
        sortRadio1.isChecked = (radioStatus.sort== POPULARITY)

        sortRadio2.text= radioStatus.listSort[1]
        sortRadio2.isChecked = (radioStatus.sort== RECENT)

        sortRadio3.text= radioStatus.listSort[2]
        sortRadio3.isChecked = (radioStatus.sort== VIEWS)

        sortRadio4.text= radioStatus.listSort[3]
        sortRadio4.isChecked = (radioStatus.sort== COMENTS)

        typeRadio1.text= radioStatus.listSort[0]
        typeRadio1.isChecked = (radioStatus.type== SHOTS)

        typeRadio2.text= radioStatus.listSort[1]
        typeRadio2.isChecked = (radioStatus.type== DEBUTS)

        typeRadio3.text= radioStatus.listSort[2]
        typeRadio3.isChecked = (radioStatus.type== TEAMS)

        typeRadio4.text= radioStatus.listSort[3]
        typeRadio4.isChecked = (radioStatus.type== PLAYOFFS)

        typeRadio5.text= radioStatus.listSort[4]
        typeRadio5.isChecked = (radioStatus.type== ANIMATED)

        typeRadio6.text= radioStatus.listSort[5]
        typeRadio6.isChecked = (radioStatus.type== ATTACHMENTS)

        typeRadio7.text= radioStatus.listSort[6]
        typeRadio7.isChecked = (radioStatus.type== REBOUNDS)
    }

    private fun initListener(){
        timeGroup.setOnCheckedChangeListener({ group, checkedId -> time = group.findViewById(checkedId).tag as String })
        typeGroup.setOnCheckedChangeListener({ group, checkedId -> type = group.findViewById(checkedId).tag as String })
        sortGroup.setOnCheckedChangeListener({ group, checkedId -> sort = group.findViewById(checkedId).tag as String })
    }

    override fun onDismiss(dialog: DialogInterface?) {
        RxBus.get().post(ShotsListFilterEvent(time, sort, type))
        super.onDismiss(dialog)
    }

}

