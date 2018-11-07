package com.ky.plan.fragment

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.ky.plan.BaseFragment
import com.ky.plan.R
import com.ky.plan.adapter.PlanAdapter
import com.ky.plan.model.BaseModel

/**
 * description: 每天安排的计划界面
 * author: kyXiao
 * date: 2018/11/7
 */
class PlanFragment : BaseFragment() {

    override fun getContentViewId(): Int {
        return R.layout.fragment_plan_layout
    }

    private val planAdapter: PlanAdapter
        get() {
            val dataList = ArrayList<BaseModel>()

            for (i in 1..10) {
                val baseModel = BaseModel("2018-11-7 23:25", "24h", "完成计划app : $i", 10)

                dataList.add(baseModel)
            }

            return PlanAdapter(context!!.applicationContext, dataList)
        }


    override fun initView(view: View) {
        val rcyPlanItem: RecyclerView = view.findViewById(R.id.rcyPlan)

        rcyPlanItem.layoutManager = LinearLayoutManager(context)
        rcyPlanItem.adapter = planAdapter
    }


    override fun initData() {

    }

    override fun loadData() {
        val dataList = ArrayList<BaseModel>()
/*
        for (i in 1..10) {
            val baseModel = BaseModel("2018-11-7 23:25", "24h", "完成计划app : $i", 10)

            dataList.add(baseModel)
        }

        planAdapter.setData(dataList)
        planAdapter.notifyDataSetChanged()*/
    }

}

