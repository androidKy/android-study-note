[TOC]
# Android中级进阶

要求：阅读Android源码，常逛开源社区如github,读写想，掌握Android的体系结构，组成方式。

- View绘制流程（Canvas绘图）

- 消息通信机制原理（Handler）

- Binder机制（Android进程间通信）

- 事件分发机制

- 屏幕适配

- AIDL与Message的使用

- 自定义组件（自定义View与自定义ViewGroup）

- 动画（补间动画、帧动画、属性动画）

- Android的签名机制（实现原理，具体操作）

- 打包机制（多渠道打包、打包流程等）

- apk安装文件压缩（压缩图片、代码压缩、.so文件压缩等）

- 数据库的使用（基本API，第三方ORM框架，ORM框架的实现原理）

- Java反射原理与简单实践

- 通知栏信息

- 版本新特性


##  View绘制流程

转载自[Android View的绘制流程](http://www.jianshu.com/p/5a71014e7b1b)

View绘制主要分为三个过程分别是measure、layout、draw。

1. **measure(int widthMeasureSpec,int heightMeasureSpec)**

  这个方法是测量View的宽高（大小），但实际测量工作是在onMeasure(int,int)方法中，只有onMeasure(int,int)方法在子类中是可以重写的，而不是measure(int,int)。

  **MeasureSpec(测量规格)**：官方解释是一个MeasureSpec封装了**从父容器传递给子容器的布局要求**。这个MeasureSpec封装的是父容器传递给子容器的布局要求，而不是父容器对子容器的布局要求，关键字是“传递”，也可以说是由父View的MeasureSpec和子view的LayoutParams通过简单的计算得出一个针对子View的测量要求，这个测量要求就是MeasureSpec。

  MeasureSpec是由大小跟模式计算出的组合值，由一个整型（32位）将size和mode打包成Int型，其中高两位是mode，后面30位是size，是为了减少对象的开支。以二进制存储，类似于下图：

  ![图片](http://upload-images.jianshu.io/upload_images/966283-c330852c971b02a8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/540)

  注:-1代表的是EXACTLY,-2是AT_MOST

  MeasureSpec一共有三种模式
  * **UPSPECIFIED**：父容器对子容器没有任何限制,子容器想多大就多大（）。
  * **EXACTLY**：父容器对子容器设置了尺寸，子容器应当服从这些边界，不论子容器想要多大的空间(match_parent,100dp精确数值)。
  * **AT_MOST**：子容器可以是声明大小内的任意大小（wrap_content)，声明的大小指的是父容器的大小。

  从代码上来看view.measure(int widthMeasureSpec,int heightMeasureSpec)的两个MeasureSpec是父类传递过来的，但并不完全是父View的要求，只是和父View有关系，**由父view的MeasureSpec和子view自己的LayoutParams共同决定的**，而子view的LayoutParams就是xml文件设置的layout_width和layout_height转化而来的。

  测量过程:父view的measure过程会先测量子view，等子view测量完成后，再来测量自己，示例代码如下：

  	protected void measureChildWithMargins(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) 
  	{ 
  	
  		// 子View的LayoutParams，你在xml的layout_width和layout_height,
  		// layout_xxx的值最后都会封装到这个LayoutParams。
  		final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();   
  		
  		//根据父View的测量规格和父View自己的Padding，
  		//还有子View的Margin和已经用掉的空间大小（widthUsed），就能算出子View的MeasureSpec,具体计算过程看getChildMeasureSpec方法。
  		final int childWidthMeasureSpec = getChildMeasureSpec(parentWidthMeasureSpec,            
  		mPaddingLeft + mPaddingRight + lp.leftMargin + lp.rightMargin + widthUsed, lp.width);    
  		
  		final int childHeightMeasureSpec = getChildMeasureSpec(parentHeightMeasureSpec,           
  		mPaddingTop + mPaddingBottom + lp.topMargin + lp.bottomMargin  + heightUsed, lp.height);  
  		
  		//通过父View的MeasureSpec和子View的自己LayoutParams的计算，算出子View的MeasureSpec，然后父容器传递给子容器的
  		// 然后让子View用这个MeasureSpec（一个测量要求，比如不能超过多大）去测量自己，如果子View是ViewGroup 那还会递归往下测量。
  		child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
  	
  	}
  	
  	// spec参数   表示父View的MeasureSpec 
  	// padding参数    父View的Padding+子View的Margin，父View的大小减去这些边距，才能精确算出
  	//               子View的MeasureSpec的size
  	// childDimension参数  表示该子View内部LayoutParams属性的值（lp.width或者lp.height）
  	//                    可以是wrap_content、match_parent、一个精确指(an exactly size),  
  	public static int getChildMeasureSpec(int spec, int padding, int childDimension)
  	 {  
  	    int specMode = MeasureSpec.getMode(spec);  //获得父View的mode  
  	    int specSize = MeasureSpec.getSize(spec);  //获得父View的大小  
  	
  	   //父View的大小-自己的Padding+子View的Margin，得到值才是子View的大小。
  	    int size = Math.max(0, specSize - padding);   
  	
  	    int resultSize = 0;    //初始化值，最后通过这个两个值生成子View的MeasureSpec
  	    int resultMode = 0;    //初始化值，最后通过这个两个值生成子View的MeasureSpec
  	
  	    switch (specMode) {  
  	    // Parent has imposed an exact size on us  
  	    //1、父View是EXACTLY的 ！  
  	    case MeasureSpec.EXACTLY:   
  	        //1.1、子View的width或height是个精确值 (an exactly size)  
  	        if (childDimension >= 0) {            
  	            resultSize = childDimension;         //size为精确值  
  	            resultMode = MeasureSpec.EXACTLY;    //mode为 EXACTLY 。  
  	        }   
  	        //1.2、子View的width或height为 MATCH_PARENT/FILL_PARENT   
  	        else if (childDimension == LayoutParams.MATCH_PARENT) {  
  	            // Child wants to be our size. So be it.  
  	            resultSize = size;                   //size为父视图大小  
  	            resultMode = MeasureSpec.EXACTLY;    //mode为 EXACTLY 。  
  	        }   
  	        //1.3、子View的width或height为 WRAP_CONTENT  
  	        else if (childDimension == LayoutParams.WRAP_CONTENT) {  
  	            // Child wants to determine its own size. It can't be  
  	            // bigger than us.  
  	            resultSize = size;                   //size为父视图大小  
  	            resultMode = MeasureSpec.AT_MOST;    //mode为AT_MOST 。  
  	        }  
  	        break;  
  	
  	    // Parent has imposed a maximum size on us  
  	    //2、父View是AT_MOST的 ！      
  	    case MeasureSpec.AT_MOST:  
  	        //2.1、子View的width或height是个精确值 (an exactly size)  
  	        if (childDimension >= 0) {  
  	            // Child wants a specific size... so be it  
  	            resultSize = childDimension;        //size为精确值  
  	            resultMode = MeasureSpec.EXACTLY;   //mode为 EXACTLY 。  
  	        }  
  	        //2.2、子View的width或height为 MATCH_PARENT/FILL_PARENT  
  	        else if (childDimension == LayoutParams.MATCH_PARENT) {  
  	            // Child wants to be our size, but our size is not fixed.  
  	            // Constrain child to not be bigger than us.  
  	            resultSize = size;                  //size为父视图大小  
  	            resultMode = MeasureSpec.AT_MOST;   //mode为AT_MOST  
  	        }  
  	        //2.3、子View的width或height为 WRAP_CONTENT  
  	        else if (childDimension == LayoutParams.WRAP_CONTENT) {  
  	            // Child wants to determine its own size. It can't be  
  	            // bigger than us.  
  	            resultSize = size;                  //size为父视图大小  
  	            resultMode = MeasureSpec.AT_MOST;   //mode为AT_MOST  
  	        }  
  	        break;  
  	
  	    // Parent asked to see how big we want to be  
  	    //3、父View是UNSPECIFIED的 ！  
  	    case MeasureSpec.UNSPECIFIED:  
  	        //3.1、子View的width或height是个精确值 (an exactly size)  
  	        if (childDimension >= 0) {  
  	            // Child wants a specific size... let him have it  
  	            resultSize = childDimension;        //size为精确值  
  	            resultMode = MeasureSpec.EXACTLY;   //mode为 EXACTLY  
  	        }  
  	        //3.2、子View的width或height为 MATCH_PARENT/FILL_PARENT  
  	        else if (childDimension == LayoutParams.MATCH_PARENT) {  
  	            // Child wants to be our size... find out how big it should  
  	            // be  
  	            resultSize = 0;                        //size为0！ ,其值未定  
  	            resultMode = MeasureSpec.UNSPECIFIED;  //mode为 UNSPECIFIED  
  	        }   
  	        //3.3、子View的width或height为 WRAP_CONTENT  
  	        else if (childDimension == LayoutParams.WRAP_CONTENT) {  
  	            // Child wants to determine its own size.... find out how  
  	            // big it should be  
  	            resultSize = 0;                        //size为0! ，其值未定  
  	            resultMode = MeasureSpec.UNSPECIFIED;  //mode为 UNSPECIFIED  
  	        }  
  	        break;  
  	    }  
  	    //根据上面逻辑条件获取的mode和size构建MeasureSpec对象。  
  	    return MeasureSpec.makeMeasureSpec(resultSize, resultMode);  
  	}

  View的**onMeasure（）**的实现
  	
  	protect void onMeasure(int widthMeasureSpec,int heightMeasureSpec)
  	{
  		setMeasureDimension(
  			getDefaultSize(getSuggestedMinmumWidth(),widthMeasureSpec),
  			getDefaultSize(getSuggestedMinmumHeight(),heightMeasureSpec));
  	}
  	
  	//获取的是android:minWidth属性的值或者view背景图片的大小值
  	protect void getSuggestedMinimumWidth()
  	{
  		return (mBackground == null) ? mMinWidth : max(mMinWidth,mBackground.getMinimumWidth());
  	}
  	
  	//size参数一般表示设置了android:minHeight属性或者该view背景图片的大小值
  	protect static void getDefaultSize(int size,int measureSpec)
  	{
  		int result = size;
  		int specMode = MeasureSpec.getMode(measureSpec);
  		int specSize = MeasureSpec.getSize(measureSpec);
  		switch(specMode)
  		{
  			case MeasureSpec.UNSEPCIFIED:	//表示该view的父视图大小未定，设置为默认值，wrap_content
  				result = size;
  				break;
  			case MeasureSpec.AT_MOST:	//match_parent
  			case MeasureSpec.EXACTLY:
  				result = specSize;
  				break;
  		}	
  		
  		return result;
  	}

  简单来说，onMeasure方法的是实现就是调用setMeasureDimension（int width,int height）方法测量宽高。

  **ViewGroup**的Measure过程

  ViewGroup类并没有实现onMeasure，但总的来说，父View是等所有的子View测量结束之后，再来测量自己。具体例子可以看下FrameLayout的测量：

  	//FrameLayout 的测量
  	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  
  		....
  		int maxHeight = 0;
  		int maxWidth = 0;
  		int childState = 0;
  		for (int i = 0; i < count; i++) {    
  		   final View child = getChildAt(i);    
  		   if (mMeasureAllChildren || child.getVisibility() != GONE) {   
  		    // 遍历自己的子View，只要不是GONE的都会参与测量，measureChildWithMargins方法在最上面
  		    // 的源码已经讲过了，如果忘了回头去看看，基本思想就是父View把自己的MeasureSpec 
  		    // 传给子View结合子View自己的LayoutParams 算出子View 的MeasureSpec，然后继续往下传，
  		    // 传递叶子节点，叶子节点没有子View，根据传下来的这个MeasureSpec测量自己就好了。
  		     measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);       
  		     final LayoutParams lp = (LayoutParams) child.getLayoutParams(); 
  		     maxWidth = Math.max(maxWidth, child.getMeasuredWidth() +  lp.leftMargin + lp.rightMargin);        
  		     maxHeight = Math.max(maxHeight, child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin);  
  		     ....
  		     ....
  		   }
  		}
  		.....
  		.....
  		//所有的孩子测量之后，经过一系类的计算之后通过setMeasuredDimension设置自己的宽高，
  		//对于FrameLayout 可能用最大的字View的大小，对于LinearLayout，可能是高度的累加，
  		//具体测量的原理去看看源码。总的来说，父View是等所有的子View测量结束之后，再来测量自己。
  		setMeasuredDimension(resolveSizeAndState(maxWidth, widthMeasureSpec, childState),        
  		resolveSizeAndState(maxHeight, heightMeasureSpec, childState << MEASURED_HEIGHT_STATE_SHIFT));
  		....
  	}

2. **layout**

  layout(int l,int t,int r,int d)的四个参数确定view的位置，onLayout只可以在ViewGroup中重写，实现自己的逻辑代码。

  具体可参考ViewGroup的onLayout方法的源码。[ViewGroup-onLayout](http://www.android-doc.com/reference/android/view/ViewGroup.html#onLayout())

3. **draw**

  這个方法很少调用，只有在View需要重新画的时候才需要重写onDraw(Canvas canvas)方法。

## 消息通信机制原理（Handler）

[参考链接1](https://www.jianshu.com/p/9fe944ee02f7)

主要看懂**Handler、Looper、MessageQueue**这三者之间的源码和它们三者之间的联系。

1. 首先创建Handler实例，在Handler构造函数会初始化Looper和MessageQueue，Looper调用loop方法进入消息循环，不断检测MessageQueue消息队列是否有消息进入，遵循先进先出原则，在调用loop方法之前必须调用prepare方法对当前线程添加Looper对象并初始化消息队列。
2. Handler通过sendMessage方法和post方法发送消息到消息队列，Looper会取出消息分派给Handler，Handler回调handlerMessage方法进行自己的处理。

## Binder机制（IPC即Android进程间通信）

[参考链接](https://blog.csdn.net/universus/article/details/6211589)

1. **为什么要用Binder机制进行IPC？**

2. **什么是Binder机制？**

3. **Binder机制原理是什么？**

* 因为Binder机制对于Android系统来说比其他IPC方式更加安全和高性能。IPC方式有三种，**共享内存、Binder、Socket/管道/消息队列**，在安全方面，传统IPC的接收方无法获得对方进程可靠的UID/PID（用户ID/进程ID），从而无法鉴别对方身份。在传输性能上，

  * **共享内存**不用拷贝数据，但控制复杂，难以使用；

  * **Socket**是一款通用接口，传输效率低且开销低，主要用在跨网络的进程间通信和本机上的进程间的低速通信，**消息队列和管道**采用存储-转发方式，即数据先从发送方缓存区拷贝到内核开辟的缓存区中，然后再从内核缓存区拷贝到接收方缓存区，至少有两次拷贝过程，效率比较低。

  * **Binder**基于**Client-Server**通信模式，传输过程只需一次拷贝，并且为发送方添加UID/PID身份，既支持匿名Binder也支持匿名Binder。

    Binder是Android系统跨进程通信（IPC）方式之一，是Client客户端和Server服务端的纽带，通过Binder可以让进程间的通信更加高效和安全。

####3、Binder机制原理
![代码执行过程](https://i.imgur.com/aJPUNls.png)

基于Client-Server通信方式，与其他IPC方式不同，Binder使用了面向对象的思想来描述作为访问接入点的Binder及其在client中的入口：**Binder是一个位于Server中的对象，该对象提供了一套方法用于实现对服务的请求，就像类的成员函数。**遍布于client中的入口可以看成指向這个Binder对象的指针，只要获得指针就可以通过Binder对象访问Server。面向对象思想的引入将进程间通信转化为通过对某个Binder对象的引用调用该对象的方法，而其独特之处在于Binder对象是一个可以跨进程引用的对象，它的实体位于一个进程中，而它的引用却遍布于系统的各个进程之中。

面向对象只是针对应用程序而言，对于Binder驱动和内核其它模块一样使用C语言实现，没有类和对象的概念。Binder驱动为面向对象的进程间通信提供底层支持。

## 事件分发机制
---

![总结图](https://upload-images.jianshu.io/upload_images/944365-e7baca065f885271.png)

[参考链接1](https://www.jianshu.com/p/38015afcdb58)

### 1.事件分发的基础认知

(1)事件分发的对象：点击事件（Touch事件），当用户触摸屏幕（view或者viewGroup）时,将产生点击事件，而事件的相关细节（发生触摸的位置、时间等）被封装成MotionEvent对象。

事件分类：

* ACTION_DOWN:按下view,所有事件的开始，只要触摸屏幕就会发生
* ACTION_MOVE：滑动view
* ACTION_UP:手指抬起view
* ACTIONI_CANCEL:结束事件(非人为）

从手指接触屏幕至手指离开屏幕，这个过程产生的一系列事件如下图：

![触摸事件发生顺序](https://upload-images.jianshu.io/upload_images/944365-79b1e86793514e99.png)

(2)事件分发的本质：将点击事件(MotionEvent)传递到某个具体的view或者viewGroup和处理的整个过程。
```
事件传递的过程 = 分发过程
```

(3)事件在哪些具体的对象之间进行传递：Activity(Fragment)、ViewGroup、View。

(4)事件在对象分发的顺序：**Activity** > **ViewGroup** > **View**

(5)事件分发过程由以下方法协作完成：

**dispatchTouchEvent()**:分发（传递）点击事件,当点击事件能够传递给传递给当前View时就会被调用。

**onTouchEvent()**:处理点击事件，在dispatchTouchEvent()内部调用。

**onInterceptTouchEvent()**:判断是否拦截了某个事件，只存在于ViewGoup,普通的view无此方法,在dispatchTouchEvent()内部调用。

![总结](https://upload-images.jianshu.io/upload_images/944365-d0a7e6f3c2bbefcc.png)
	

### 2.事件分发机制的源码分析




### 3.工作流程
### 4.核心方法
### 5.常见的事件分发场景
### 6.其他事件分发
### 7.总结 

### Serizliable和Parcelable两种序列化原理和对比



### SparseArray、ArrayMap和HashMap的实现原理和对比