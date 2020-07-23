# Message-demo
# 通过Messenge演示两个进程进行通信<br>
## 步骤
### 1、服务端创建一个Messenger，然后在onBind回调中把binder返回。<br>
### 2、客户端绑定服务端，在onServiceConnected回调中利用IBinder获取到服务端的Messenger。创建一个message，用于给服务端发送消息，然后设置replyTo,监听服务端回的消息<br>
### 3、服务端收到客户端的消息后，给客户端回一个消息msg.replyTo.send
