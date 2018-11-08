package com.ky.plan.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.ky.plan.R
import com.ky.plan.model.BaseModel
import com.ky.plan.uitl.LogUtil
import kotlinx.android.synthetic.main.activity_alter.*

class AlterActivity : AppCompatActivity() {
    private val TAG = "AlterActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtil.printI(TAG, "onCreate")

        setContentView(R.layout.activity_alter)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onStart() {
        super.onStart()
        LogUtil.printI(TAG, "onStart")
        initData()
    }


    private fun initData() {
        val bundle = intent.extras?.getBundle("BUNDLE_KEY")
        val baseModel = bundle?.getSerializable("BASEMODEL_KEY") as BaseModel

        bt_timeStart.text = baseModel.startTime
        bt_timeLength.text = baseModel.timeLength
        tv_progress.text = baseModel.progress.toString()
        et_content.setText(baseModel.content)
        et_content.setSelection(baseModel.content.length)
    }

    override fun onPause() {
        super.onPause()
        LogUtil.printI(TAG, "onPause")

    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtil.printI(TAG, "onDestroy")
    }

    fun finishedChange(view: View) {
        val intent = Intent()
        val bundle = Bundle()
        val baseModel = BaseModel(bt_timeStart.text.toString(), bt_timeLength.text.toString(),
                et_content.text.toString(), tv_progress.text.toString().toInt())
        bundle.putSerializable("BASEMODEL_KEY", baseModel)
        intent.putExtra("BUNDLE_KEY", bundle)
        setResult(2000, intent)
        finish()
    }
}
