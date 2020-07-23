package com.jokchen.message_demo

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log

class MessageService : Service() {
    private val TAG: String = "MessageService"
    private val mMessage: Messenger = Messenger(object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val bundle: Bundle = msg.data
            val clientMsg: String? = bundle.getString("client")
            Log.i(TAG, "来自客户端的消息：" + clientMsg)
            //新建一个Message对象，作为回复客户端的对象
            val message: Message = Message.obtain()
            val bundle1: Bundle = Bundle()
            bundle1.putString("service", "今天就不去了，还有很多东西要学！！");
            message.data = bundle1;
            msg.replyTo.send(message);
        }
    })

    override fun onBind(intent: Intent): IBinder {
        return mMessage.binder
    }
}
