[TOC]

# BatmobiStudy

## JS调试

* **电脑端浏览器调试**：快捷键F12进入开发者模式调试。

* **远程调试网页**：把手机通过usb线连接到电脑，通过电脑端的Google浏览器调试。

  1. 手机先用usb连接电脑，并打开开发者模式，允许电脑进行调试。
  2. 打开电脑端Google浏览器，输入 **chrome://inspect/#devices** ，选中左侧菜单栏的 **Devices** 选项。
  3. 在手机端浏览器打开需要调试的网址，然后在Google浏览器可以看到正在连接的设备和显示的浏览器。
  4. 最后点击inspect打开调试。

* **远程调试WebView**：把手机通过usb线连接到电脑，通过电脑端的Google浏览器调试。

  1. 手机先用usb连接电脑，并打开开发者模式，允许电脑进行调试。

  2. 在您的原生 Android 应用中启用 WebView 调试；

     ~~~java
     //在应用中启用WebView调试
     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
         WebView.setWebContentsDebuggingEnabled(true);
     }
     ~~~

  3. 然后调试webView与通过远程调试调试网页相同
  
  （注意：由于无法访问 [https://chrome-devtools-frontend.appspot.com](https://chrome-devtools-frontend.appspot.com/)，会出现空白页面，解决办法有两个：一、简单直接翻墙；二、添加离线包，[参考教程](https://www.cnblogs.com/slmk/p/9832081.html)

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

5. 打开Charles软件，当手机端访问数据时，Charles就可以抓到这些链接并查看请求和返回数据。

6. Charles模拟数据调试：（原理是访问链接之前进行拦截并对请求头进行修改）

   * 右键单击需要调试的链接，选择 **Breakpoints** 。
   * 当手机端再次访问该链接时，Charles会自动跳转到Breakpoint选项。
   * 修改发送的请求参数：点击链接—Edit Request—text。
   * 修改完成后点击 execute。

7. 如何对Https进行抓包？

   1. 为手机设置代理，操作步骤如上面第4所示。

   2. 设置代理成功后，打开手机浏览器输入 **chls.pro/ssl** 下载证书并进行安装。（注：这必须是设置代理成功后才能安装证书。）

   3. 电脑端的Charles的根证书安装，**help** -> **SSL Proxying** -> **Install Charles Root Certificate** 。

   4. 设置访问域名和端口。**Proxy** -> **SSL Proxying Settings** -> **SSL Proxying**。

   5. 如果是Android 7.0以上的机子，以上配置不会生效，需要在代码进行证书配置。

   6. 导出电脑端的Charles的证书，命名为**charles_certificate.pem**。**htlp** -> **SSL Proxyinig** -> **Save Charles Root Certificate**。

   7. 在项目的res目录下新增一个文件夹，命名xml，并且新建一个xml文件，命名为 **network_security_config.xml** ，名字可以随便命名，对应即可。

   8. 把电脑端导出的Charles证书放在res/raw目录下，如果没有raw目录需要新建。

   9. network_security_config.xml添加以下内容:

      ~~~xml
      <?xml version="1.0" encoding="utf-8"?>
      <network-security-config>
          <domain-config>
              <!--可以添加多个域名 -->
              <domain includeSubdomains="true">
                  www.baidu.com
              </domain>
              <domain includeSubdomains="true">
                  www.youtube.com
              </domain>
              <!-- 可以添加多个Charles证书 -->
              <trust-anchors>
                  <certificates src="@raw/charles_certificate" />
                  <certificates src="@raw/charles_certificate2" />
              </trust-anchors>
          </domain-config>
      </network-security-config>
      ~~~


## FTP文件上传和下载

> 必须先通过账号和密码连接上FTP才能进行文件上传和下载。

* **连接FTP：**
* **文件上传：**
* **文件下载：**

## Proguard混淆规则

> 混淆主要目的是为了不想开源应用，加大反编译的成本，但是并不能彻底防止反编译。还有一些其他好处，比如压缩代码，去掉无用的代码资源，有利于减少APK的体积。

> 在项目中的build.gradle文件中。
>
> * 设置混淆文件：android - buildTypes - release 里面：
>
>   **proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'**
>
>   proguard-rules.pro就是项目的混淆文件，默认生成，内容为空。
>
> * 开启混淆：找到minifyEnabled这个配置,然后设置为true即可。

### 混淆的常见配置

> [参考链接1](https://www.jianshu.com/p/b5b2a5dfaaf4)
>
> [参考链接2](https://www.jianshu.com/p/90feb5c50cce)

* Proguard关键字

  | Proguard关键字             | 描述                                                         |
  | -------------------------- | ------------------------------------------------------------ |
  | dontwarn                   | 关闭警告。dontwarn是一个和keep可以说是形影不离,尤其是处理引入的library时。 |
  | keep                       | 保留类和类中的成员，防止被混淆或移除                         |
  | keepnames                  | 保留类和类中的成员，防止被混淆，成员没有被引用会被移除       |
  | keepclassmembers           | 只保留类中的成员，防止被混淆或移除                           |
  | keepclassmembernames       | 只保留类中的成员，防止被混淆，成员没有引用会被移除           |
  | keepclasseswithmembers     | 保留类和类中的成员，防止被混淆或移除，保留指明的成员         |
  | keepclasseswithmembernames | 保留类和类中的成员，防止被混淆，保留指明的成员，成员没有引用会被移除 |

  ```kotlin
  //不混淆某个类
  -keep public class name.huihui.example.Test { *; }
  //不混淆某个类的子类
  -keep public class * extends name.huihui.example.Test { *; }
  //不混淆所有类名中包含了“model”的类及其成员
  -keep public class **.*model*.** {*;}
  //不混淆某个接口的实现
  -keep class * implements name.huihui.example.TestInterface { *; }
  //不混淆某个类的构造方法
  -keepclassmembers class name.huihui.example.Test { 
      public <init>(); 
  }
  //不混淆某个类的特定的方法
  -keepclassmembers class name.huihui.example.Test { 
      public void test(java.lang.String); 
  }
  //不混淆某个类的内部类
  -keep class name.huihui.example.Test$* {
          *;
   }
  //两个常用的混淆命令，注意：
  //一颗星表示只是保持该包下的类名，而子包下的类名还是会被混淆；
  //两颗星表示把本包和所含子包下的类名都保持；
  -keep class com.suchengkeji.android.ui.**
  -keep class com.suchengkeji.android.ui.*
  //用以上方法保持类后，你会发现类名虽然未混淆，但里面的具体方法和变量命名还是变了，
  //如果既想保持类名，又想保持里面的内容不被混淆，我们就需要以下方法了
  
  //不混淆某个包所有的类
  -keep class com.suchengkeji.android.bean.** { *; }
  //在此基础上，我们也可以使用Java的基本规则来保护特定类不被混淆，比如我们可以用extend，implement等这些Java规则。如下
  # 保留我们使用的四大组件，自定义的Application等等这些类不被混淆
  # 因为这些子类都有可能被外部调用
  -keep public class * extends android.app.Activity
  -keep public class * extends android.app.Appliction
  -keep public class * extends android.app.Service
  -keep public class * extends android.content.BroadcastReceiver
  -keep public class * extends android.content.ContentProvider
  -keep public class * extends android.app.backup.BackupAgentHelper
  -keep public class * extends android.preference.Preference
  -keep public class * extends android.view.View
  -keep public class com.android.vending.licensing.ILicensingService
  ```

  

* Proguard通配符

  | Proguard通配符 | 描述                                 |
  | -------------- | ------------------------------------ |
  | \<field\>      | 匹配类中的所有字段                   |
  | \<method>      | 匹配类中的所有方法                   |
  | \<init>        | 匹配类中的所有构造函数               |
  | \*             | 匹配任意长度字符，不包含包名分隔符 . |
  | \*\*           | 匹配任意长度字符，包含包名分隔符     |
  | \***           | 匹配任意参数类型                     |

* 其他混淆配置

  | 混淆配置                               | 混淆说明                                                     |
  | -------------------------------------- | ------------------------------------------------------------ |
  | -optimizationpasses 5                  | 代码混淆压缩比，在0~7之间，默认为5，一般不做修改             |
  | -dontusemixedcaseclassnames            | 混淆时不适用大小写混合，混合后的类名为小写                   |
  | -printmapping ./build/mapping_text.txt | 打印映射规则到指定目录下的文件（自动生成），为以后修复bug方便 |
  | -dontpreverify                         | 不做预校验,去掉这一步能加快混淆速度                          |
  | -dontoptimize                          | 不优化代码                                                   |

## 反编译

>对APK进行反编译，查看源码。

### 工具

1. **zip**压缩工具：先把apk的后缀名改成 **.zip**，然后解压得到classes.dex文件（二进制class文件)。
2. **dex2jar**工具：把classes.dex文件放到dex2jar根目录下，然后运行cmd命令：**d2j-dex2jar classes.dex** 把.dex文件反编译成jar包。
3. **jd-gui**工具：查看jar。(ps:下载安装完成后可以在path路径下配置该工具的目录根路径，然后在cmd窗口输入命令：**jd-gui**直接打开)
4. **jadx**工具：既可以查看jar，也可以直接反编译apk文件。

## ant打包脚本

> 在Android Studio中可以通过ant脚本实现统一自动打包测试和发布apk。减去手动打包的麻烦。

* 替换字符：
  * 更改包名。
  * 更改版本号。
  * 更改apk和jar包名字。
* 运行Android Studio的app/build.gradle文件的task进行编译打包apk。
* 通过脚本进行压缩。

[注]Ant的详细内容可参考[Ant.md文件](https://github.com/androidKy/android-study-note/blob/master/note/Ant.md)。

## 爬取素材

> 通过反射获取对象的参数，得到所需要的素材。例子：爬取Facebook的广告sdk素材。

1. 先通过断点调试找到所需要的素材所在那个广告对象的层级位置。
2. 通过反射获取到广告对象的那些素材。

代码示例：

~~~java
public JSONObject reflectObj(Object object) throws Exception{
    
    	Field controllerField = object.getClass().getDeclaredField("e");
        controllerField.setAccessible(true);
        Object controller =  controllerField.get(object);

        Field field = controller.getClass().getDeclaredField("r");
        field.setAccessible(true);
        Object cObj = field.get(controller);
        Field aField = cObj.getClass().getDeclaredField("a");
        aField.setAccessible(true);

        ArrayList dataList = (ArrayList) aField.get(cObj);
        if (dataList != null && dataList.size() > 0) {
            Object dataObj = dataList.get(0);
            Field bField = dataObj.getClass().getDeclaredField("b");
            bField.setAccessible(true);
            JSONObject jsonObject = (JSONObject) bField.get(dataObj);
            return jsonObject;
        }
        return null;
}
~~~

## Android备份和还原

> 详情可参考本人[GitHub](https://github.com/androidKy/BackupProject)

## 其他

### adb截图命令

1. adb shell screencap -p /sdcard/screenName.png
2. adb pull /sdcard/screenName.png 
3. adb shell rm /sdcard/screenName.png