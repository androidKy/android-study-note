package com.ky.plan

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ky.plan.uitl.LogUtil

/**
 * description: Fragment的基类
 * author: kyXiao
 * date: 2018/11/7
 */
abstract class BaseFragment : Fragment() {

    private val TAG = "BaseFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        LogUtil.printI(TAG, "onCreateView")

        val view = inflater.inflate(getContentViewId(), container, false)

        initView(view)

        initData()

        return view
    }

    abstract fun getContentViewId(): Int

    abstract fun initView(view: View)

    abstract fun initData()

    abstract fun loadData()
}