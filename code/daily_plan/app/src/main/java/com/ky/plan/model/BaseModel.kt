package com.ky.plan.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

/**
 * Created by kyXiao
 * 2018/11/7.
 */
data class BaseModel(var startTime: String, var timeLength: String,
                     var content: String, var progress: Int) : Serializable