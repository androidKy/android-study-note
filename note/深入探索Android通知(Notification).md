# 深入探索Android通知(Notification)

[TOC]

## 前言

本篇是对Android通知的详细内容介绍和使用（包括Android 10）。通知是指 Android 在应用界面之外显示的消息，旨在向用户提供提醒、来自他人的通信信息或应用中的其他及时信息。用户可以点击通知来打开应用，或直接从通知中执行一些操作。

## 一、实现原理

简单概述：

### 源码

```java
/** android.app.NotificationManager
 * 发起通知,tag和id在你的应用中必须是唯一的。
 * tag:通知的唯一标识字符型。可以为null。
 * id:通知的唯一标识整数型，当发起的通知id相同时，表示是同一个通知，如果通知存在不会重新创建发起通知，而是 
 * 更新，不相同时，会重新创建发起一个新的通知。
 * notification:通知的定义，包括UI、级别/重要性、渠道、类型等属性
 */
public void notify(String tag, int id, Notification notification){
    notifyAsUser(tag, id, notification, mContext.getUser());
}

public void notifyAsUser(String tag, int id, Notification notification, UserHandle user){
        INotificationManager service = getService();
        String pkg = mContext.getPackageName();

        try {
            if (localLOGV) Log.v(TAG, pkg + ": notify(" + id + ", " + notification + 		 			")");
            //service是系统创建的通知服务，对应NotificationManagerService类,实际上通知是由它发起
            service.enqueueNotificationWithTag(pkg, mContext.getOpPackageName(), tag, id,
                    fixNotification(notification), user.getIdentifier());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
}


```



## 二、 