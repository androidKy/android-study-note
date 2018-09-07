package com.ky.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    var varObj = "hello world"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "sample"

        println(getString(varObj, 2))
    }

    private fun getString(var1: String, var2: Int): String {
        return var1
    }
}
