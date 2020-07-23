package com.jokchen.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG:String = "client MainActivity"
    val mMessageClient:MessageClient = MessageClient()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBtnListener()
    }

    fun initBtnListener(){
        btn_bind_service.setOnClickListener {
            Log.i(TAG,"开始绑定服务")
            mMessageClient.bind(this)
        }
        btn_unbind_service.setOnClickListener {
            Log.i(TAG,"开始取消绑定服务")
            mMessageClient.unbind(this)
        }
    }
}
