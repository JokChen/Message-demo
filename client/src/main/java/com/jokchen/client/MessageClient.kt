package com.jokchen.client

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.util.Log


/**
 * @brief
 * @author  chenxiuning
 * @since 2020/7/22 22:54
 */
class MessageClient() {
    private val TAG: String = "MessageClient"
    private val SERVER_PACKAGE_NAME: String = "com.jokchen.message_demo"
    private val SERVER_CLASS_NAME: String = "com.jokchen.message_demo.MessageService"
    private val mIntent: Intent = Intent()
    private val mServiceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            Log.i(TAG, "成功解除绑定服务")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.i(TAG, "成功绑定服务")
            // 获取服务端关联的Messenger对象
            val serviceMessager: Messenger = Messenger(service)
            //创建Message对象
            val message: Message = Message.obtain()
            val bundle: Bundle = Bundle()
            bundle.putString("client", "今天出去玩吗？")
            message.data = bundle
            //在message中添加一个回复mRelyMessenger对象
            message.replyTo = Messenger(object : Handler() {
                override fun handleMessage(msg: Message) {
                    super.handleMessage(msg)
                    val bundle = msg.data
                    val serviceMsg = bundle.getString("service")
                    Log.i(TAG, "来自服务端的回复：$serviceMsg")
                }
            })
            serviceMessager.send(message);
        }

    }

    init {
        // 初始化intent
        mIntent.component = ComponentName(
            SERVER_PACKAGE_NAME,
            SERVER_CLASS_NAME
        )
    }

    fun bind(context: Context) {
        val result: Boolean =
            context.bindService(mIntent, mServiceConnection, Context.BIND_AUTO_CREATE)
        Log.i(TAG, "bind result = $result")
    }

    fun unbind(context: Context) {
        context.unbindService(mServiceConnection)
    }
}