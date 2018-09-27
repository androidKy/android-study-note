# BatmobiStudy

## JS调试

* 电脑端浏览器调试：快捷键F12进入开发者模式调试。
* 手机端浏览器调试：把手机通过usb线连接到电脑，通过电脑端的Google浏览器调试。
  * 手机先用usb连接电脑，并打开开发者模式，允许电脑进行调试。
  * 打开电脑端Google浏览器，输入 **chrome://inspect/#devices** ，选中左侧菜单栏的 **Devices** 选项。
  * 在手机端浏览器打开需要调试的网址，然后在Google浏览器可以看到正在连接的设备和显示的浏览器。
  * 最后点击inspect打开调试。

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

> [参考链接](https://www.jianshu.com/p/b5b2a5dfaaf4)

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

  1. 保留某个包下面的类以及子包：

     **-keep public class com.batmobi.ky.\*\*{\*;} **

     注：两个**表示保留子包，一个\*只保留当前包下的类。

  2. 保留某个类的方法和成员变量：

     **-keep public class com.batmobi.ky.ClassName{**

     	**public \<methods\>;**
	
     	**public \<fields\>;**

     **}**

  3. 保留实体类的set和get方法

     **-keep public class com.batmobi.ky.bean.\*\*{**

     	**public void set\*(\*\*\*);**
	
     	**public \*\*\* get*() ;**
	
     	**public \*\*\* is\*();**

     **}**

  4. 不混淆某个类

     **-dontwarn com.batmobi.ky.ClassName**

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

## ant打包脚本

> 在Android Studio中可以通过ant脚本实现统一自动打包测试和发布apk。减去手动打包的麻烦。

* 替换字符：
  * 更改包名。
  * 更改版本号。
  * 更改apk和jar包名字。
* 运行Android Studio的app/build.gradle文件的task进行编译打包apk。
* 通过脚本进行压缩。

[注]Ant的详细内容具体可参考Ant.md文件。

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