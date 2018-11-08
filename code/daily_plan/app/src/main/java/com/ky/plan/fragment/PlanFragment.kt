package com.ky.plan.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.ky.plan.BaseFragment
import com.ky.plan.OnItemClickCallBack
import com.ky.plan.R
import com.ky.plan.activity.AlterActivity
import com.ky.plan.adapter.PlanAdapter
import com.ky.plan.model.BaseModel
import com.ky.plan.uitl.Constants
import com.ky.plan.uitl.LogUtil


/**
 * description: 每天安排的计划界面
 * author: kyXiao
 * date: 2018/11/7
 */
class PlanFragment : BaseFragment() {
    private val TAG = "PlanFragment"

    var mDataList = ArrayList<BaseModel>()
    var mDataChangedIndex = -1


    private val planAdapter: PlanAdapter
        get() {
            return PlanAdapter(context!!.applicationContext, mDataList, ItemClickCallBack())
        }

    override fun getContentViewId(): Int {
        return R.layout.fragment_plan_layout
    }

    inner class ItemClickCallBack : OnItemClickCallBack {
        override fun onItemClick(view: View, position: Int) {
            LogUtil.printI("item : $position " + mDataList[position].toString())
            mDataChangedIndex = position

            val intent = Intent(activity, AlterActivity::class.java)
            val data = Bundle()
            data.putSerializable("BASEMODEL_KEY", mDataList[position])
            intent.putExtra("BUNDLE_KEY", data)
            startActivityForResult(intent, 1000)
        }
    }

    override fun initView(view: View) {
        val rcyPlanItem: RecyclerView = view.findViewById(R.id.rcyPlan)

        rcyPlanItem.layoutManager = LinearLayoutManager(context)
        rcyPlanItem.adapter = planAdapter
    }


    override fun initData() {
        LogUtil.printI(TAG, "planAdapter init")
        for (i in 1..20) {
            val baseModel = BaseModel("2018-11-7 23:25", "24h", "完成计划app : $i", 20)

            mDataList.add(baseModel)
        }

       // planAdapter.notifyDataSetChanged()
    }

    override fun loadData() {
/*
        for (i in 1..10) {
            val baseModel = BaseModel("2018-11-7 23:25", "24h", "完成计划app : $i", 10)

            dataList.add(baseModel)
        }


        planAdapter.setData(dataList)
        planAdapter.notifyDataSetChanged()*/
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000) {
            val bundle = data?.extras?.getBundle("BUNDLE_KEY")
            val baseModel = bundle?.getSerializable("BASEMODEL_KEY") as BaseModel

            mDataList[mDataChangedIndex] = baseModel
            planAdapter.setDatas(mDataList)

        }
    }
}

