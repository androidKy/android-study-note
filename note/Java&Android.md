[TOC]

# Java

## 一、基本概念和语法特征

### 1、面向对象编程的三大特征

* **封装**：对象要有一个明确的边界；边界的划分（对象各司其职、对象的粒度、对象的可重用性）。
* **继承**：共性放到父类，特性放到子类；父类 --> 子类 --> 一般。
* **多态**：指允许不同类的对象对同一消息做出响应。即同一消息可以根据发送对象的不同而采用多种不同的行为方式。

### 2、抽象类和接口的意义和区别



### 3、内部类的作用

### 4、重写和重载

### 5、虚拟机

### 6、内存模型

### 7、垃圾回收机制

## 二、知识点

### 1、Java集合、容器、队列

### 2、多线程

### 3、进程

### 4、同步和并发（Synchronize和Asynchronize、Volidate等关键字的作用）

### 5、锁（死锁、乐观锁、悲观锁等）

### 6、排序算法

# Android

## 一、UI

1. 动画
   [参考链接](https://www.jianshu.com/p/609b6d88798d)

   * 逐帧动画

     逐帧动画的原理就是让一系列的静态图片依次播放，利用人眼“视觉暂留”的原理，实现动画。用法：用多张图片在很短的时间内持续播放。

   * 补间动画

     补间动画就是指开发者指定动画的开始、动画的结束的"关键帧"，而动画变化的"中间帧"由系统计算，并补齐。

   * 属性动画

     属性动画可以看作是增强版的补间动画，与补间动画的不同之处体现在：

     1. 补间动画只能定义两个关键帧在透明、旋转、位移和倾斜这四个属性的变换，但是属性动画可以定义任何属性的变化。
   
   一、补间动画只能对 UI 组件执行动画，但属性动画可以对任何对象执行动画。
   
   相同之处：
   
   1. **动画持续时间**。默认为 300ms，可以通过 android:duration 属性指定。
   2. **动画插值方式**。通过 android:interploator 指定。
   3. **动画重复次数**。通过 android:repeatCount 指定。
   4. **重复行为**。通过 android:repeatMode 指定。
   5. **动画集**。在属性资源文件中通过 <set .../> 来组合。
   6. **帧刷新率**。指定多长时间播放一帧。默认为 10 ms。
   
   二、属性动画的使用
   
   ```java
   /**
    * TypeEvaluator决定了动画如何从初始值过渡到结束值
    * fraction：当前动画完成的百分比
    * startValue: 初始值
    * endValue:结束值
    */
   public class PointSinEvaluator implements TypeEvaluator {
       @Override
       public Object evaluate(float fraction, Object startValue, Object endValue) {
           Point startPoint = (Point) startValue;
           Point endPoint = (Point) endValue;
           float x = startPoint.getX() + fraction * (endPoint.getX() - startPoint.getX());
   
           float y = (float) (Math.sin(x * Math.PI / 180) * 100) + endPoint.getY() / 2;
           Point point = new Point(x, y);
           return point;
       }
   }
   
   public void main(String[] args){
     			Point startP = new Point(RADIUS, RADIUS);//初始值（起点）
           Point endP = new Point(getWidth() - RADIUS, getHeight() - RADIUS);//结束值（终点）
           final ValueAnimator valueAnimator = ValueAnimator.ofObject(new PointSinEvaluator(), 																																		startP, endP);
           valueAnimator.setRepeatCount(-1);
           valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
           valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
               @Override
               public void onAnimationUpdate(ValueAnimator animation) {
                   currentPoint = (Point) animation.getAnimatedValue();
                   postInvalidate();
               }
           });
     			valueAnimator.start();
   }
   ```
   
   三、[**Lottie**](https://airbnb.design/introducing-lottie/)的使用和实现原理

## 二、四大组件

## 三、Handler的机制

## 四、Android各版本的特性和区别

## 五、H5和WebView的使用和原理

## 六、JNI开发

[参考文章：Gityuan的博客JNI原理](http://gityuan.com/2016/05/28/android-jni/)

[Google JNI详解](https://developer.android.com/training/articles/perf-jni)

* 什么是JNI

  **JNI**：java native interface.Java本地接口。这不是Android系统独有的，而是Java所有。Java语言是跨平台的语言，而这跨平台的背后都是依靠Java虚拟机，虚拟机采用C/C++编写，适配各个系统，通过JNI为上层Java提供各种服务，保证跨平台性。

* JNI用来干什么的
  Android系统通过JNI和native层交互，Java层可以直接调用Native接口。

* JNI怎么用

  

* JNI的原理

## 七、常用的第三方类库

* 依赖注入的[**Dagger**](https://github.com/google/dagger)
* 

## 八、Android的设计模式和六大原则

## 九、内存优化和性能提高

## 十、其他

### 1、ANR的定位和修正

### 2、OOM和内存泄漏

### 3、热更新

### 4、插件化

### 5、减小APK体积





