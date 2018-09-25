# BatmobiStudy

## JS调试

* 电脑端浏览器调试：快捷键F12进入开发者模式调试。
* 手机端浏览器调试：把手机通过usb线连接到电脑，通过电脑端的Google浏览器调试。
  * 手机先用usb连接电脑，并打开开发者模式，允许电脑进行调试。
  * 打开电脑端Google浏览器，输入 **chrome://inspect/#devices** ，选中左侧菜单栏的 **Devices** 选项。
  * 在手机端浏览器打开需要调试的网址，然后在Google浏览器可以看到正在连接的设备和显示的浏览器。
  * 最后点击inspect打开调试就完成。

## Charles抓包测试

1. 先安装Charles软件。[官方下载网址](https://www.charlesproxy.com/)

2. 破解Charles：打开Charles进行注册。

   * RegistedName: https://zhile.io
   * License Key: 48891cf209c6d32bf4

3. 关闭windows的防火墙：（win10）控制面板—系统和安全—windows defender 防火墙。

4. 手机网络配置电脑端的代理：

   * 先查找电脑端的IP地址：
     * 在电脑端打开cmd命令窗口（快捷键window+R,输入cmd)：输入命令 **ipconfig** 。IPv4地址就是电脑端的IP地址，例如：192.168.5.93。
   * 如果手机端已启动VPN代理，必须关闭VPN。没有则忽略。

   * 长按连接的WiFi网络，选择修改网络：
     * 高级选项—代理—手动。
     * 代理服务器主机名：填入电脑端查找的IP地址192.168.5.93。
     * 代理服务器端口：固定是8888。

5. 打开Charles软件，当手机端访问数据时，Charles就可以抓到这些链接了。

6. Charles模拟数据调试：（原理是访问链接之前进行拦截并对请求头进行修改）

   * 右键单击需要调试的链接，选择 **Breakpoints** 。
   * 当手机端再次访问该链接时，Charles会自动跳转到Breakpoint选项。
   * 修改发送的请求参数：点击链接—Edit Request—text。
   * 修改完成后点击 execute。

## FTP文件上传和下载

> 必须先通过账号和密码连接上FTP才能进行文件上传和下载。

* **连接FTP：**

* **文件上传：**

* **文件下载：**

## Ant

> 在Android Studio中可以通过ant脚本实现统一自动打包测试和发布apk。减去手动打包的麻烦。

* 替换字符：
  * 更改包名。
  * 更改版本号。
  * 更改apk和jar包名字。
* 运行Android Studio的app/build.gradle文件的task进行编译打包apk。
* 通过脚本进行压缩。