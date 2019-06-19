# adb命令

## 取出手机中已安装的apk

1. 查看手机中安装的apk列表

   > adb shell pm list package

2. 根据包名找出apk在内部存储空间的路径

   > adb shell pm path com.percent.tamer

3. 使用adb pull命令将apk文件导出

   > adb pull  /data/app/com.percent.tamer-1/base.apk tamer.apk

## 从电脑传文件到手机上

> adb push C:\workSpace\com.percent.wildtamer.apk /sdcard/p2pAuto/com.percent.wildtamer.apk

## 打开指定应用详情业

> 需要手动设置 默认 应用市场，不然在同时安装多个应用市场的情况下，会弹出选择 应用市场 界面
>
> adb shell am start -a android.intent.action.VIEW -d  market://details?id=com.tencent.mm 

## 打开 指定应用市场 指定应用详情页

> 不需要设置默认应用市场，同时安装多个应用市场的情况下，不会弹出 应用市场选择界面
>
> adb shell am start -a android.intent.action.VIEW -d  market://details?id=com.tencent.mm -p com.huawei.appmarket
>
> [参考链接](https://blog.csdn.net/slimboy123/article/details/54016830)

## 快速定位 Android APP 当前页面（Activity / Fragment）
> Android 如何快速定位当前页面是哪个Activity or Fragment
>
> (1)查看当前Activity ：adb shell "dumpsys window w | grep name="
>
> (2)查看当前栈顶的Activity ：adb shell dumpsys activity | grep "mFocusedActivity"
>
> (3)查看当前栈顶的Activity的Fragment ：adb shell dumpsys activity your.package.name
> 或者：
> adb shell dumpsys activity top
>
> 查看帮助：adb shell dumpsys activity -h

 # Linus命令

## 导入文件到sytem高权限的目录下

1. $su
2. #mount
3. #mount -o remount /dev/block/mtdblock0 /system
4. #mount
5. #cp /sdcard/tcpdump /system/bin 

