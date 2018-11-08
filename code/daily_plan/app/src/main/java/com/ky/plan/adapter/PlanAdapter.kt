package com.ky.plan.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.ky.plan.OnItemClickCallBack
import com.ky.plan.R
import com.ky.plan.model.BaseModel
import com.ky.plan.uitl.LogUtil

/**
 * Created by kyXiao
 * 2018/11/7.
 */
class PlanAdapter(context: Context, datas: List<BaseModel>, onItemClickCallBack: OnItemClickCallBack)
    : RecyclerView.Adapter<PlanAdapter.PlanViewHolder>() {
    private val TAG = "PlanAdapter"

    private var mContext = context
    private var mDatas = datas
    private var mOnItemClickCallBack = onItemClickCallBack


    fun setDatas(datas: List<BaseModel>) {
        this.mDatas = datas

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PlanViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_plan, p0, false)
        return PlanViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mDatas.size
    }

    override fun onBindViewHolder(viewHolder: PlanViewHolder, index: Int) {
        LogUtil.printI(TAG, "onBindViewHolder")

        val dataModel = mDatas[index]
        viewHolder.tvStartTime.text = dataModel.startTime
        viewHolder.tvTimeLength.text = dataModel.timeLength
        viewHolder.tvContent.text = dataModel.content
        viewHolder.pbProcess.progress = dataModel.progress

        viewHolder.itemView.setOnClickListener {
            mOnItemClickCallBack.onItemClick(it, index)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }


    class PlanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvStartTime = itemView.findViewById<TextView>(R.id.tvStartTime)!!
        val tvTimeLength = itemView.findViewById<TextView>(R.id.tvTimeLength)!!
        val tvContent = itemView.findViewById<TextView>(R.id.tvContent)!!
        val pbProcess = itemView.findViewById<ProgressBar>(R.id.pbProgress)!!
    }

    //这是java实现回调监听的方式
    /*private var onItemClickListener: OnItemClickListener? = null


    fun setOnItemClickListener(clickListener: OnItemClickListener?) {
        this.onItemClickListener = clickListener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, data: BaseModel)
    }*/
    //kotlin实现回调监听的方式
    /*  lateinit var mListener: (Int, BaseModel) -> Unit

      fun setOnItemClickListener(onItemClickListener: (Int, BaseModel) -> Unit) {
          this.mListener = onItemClickListener
      }*/
}