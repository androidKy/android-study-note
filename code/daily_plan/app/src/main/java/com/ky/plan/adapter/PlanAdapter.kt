package com.ky.plan.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ky.plan.R
import com.ky.plan.model.BaseModel

/**
 * Created by kyXiao
 * 2018/11/7.
 */
class PlanAdapter(private val context: Context, private var datas: List<BaseModel>) : RecyclerView.Adapter<PlanAdapter.PlanViewHolder>() {

    fun setData(datas: List<BaseModel>) {
        this.datas = datas
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PlanViewHolder {

        return PlanViewHolder(LayoutInflater.from(context).inflate(R.layout.item_plan, p0, false))
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(viewHolder: PlanViewHolder, index: Int) {
        val dataModel = datas[index]
        viewHolder.tvStartTime.text = dataModel.startTime
        viewHolder.tvTimeLength.text = dataModel.timeLength
        viewHolder.tvContent.text = dataModel.content
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }


    class PlanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvStartTime = itemView.findViewById<TextView>(R.id.tvStartTime)
        val tvTimeLength = itemView.findViewById<TextView>(R.id.tvTimeLength)
        val tvContent = itemView.findViewById<TextView>(R.id.tvContent)
    }
}