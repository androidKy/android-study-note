**广州益玩网络科技有限公司**

	没有笔试，先HR来问一下之前的工作经历，和为什么想换一份工作，对薪资的期望多少，然后接着是面试官。
	
	（1）自我介绍
		
	（2）曾经遇到的难题
	
		答：手势事件冲突。
	
	（3）对之前做过的产品的介绍和功能点
	
	（4）多线程的使用
		
		答：进程是指一个进行中的程序，一个进程包含多个线程（至少包含一个线程），线程是进程中一个独立的控制单元，使用多线程最好不要不断创建
		新线程（会消耗过多的资源），可以采用线程池进行管理，复用一些已经完成任务的但仍活着的线程。
	
	（5）怎么优化内存
		
		答：1、防止内存溢出和泄漏；2、尽量复用一些可以复用的对象；3、用一些较轻量级的容器，比如SparseArray；4、尽量避免用枚举类型；
		5、ListView的convertView的复用;6、数据相关：序列化数据使用protobuf可以比xml省30%内存，慎用shareprefercnce，因为对于同一个sp，
		会将整个xml文件载入内存，有时候为了读一个配置，就会将几百k的数据读进内存，数据库字段尽量精简，只读取所需字段。
		7、dex优化，代码优化，谨慎使用外部库。8、图片压缩。9、缓存池的大小（LruCache)：图片的缓存，不用经常被GC。
	
	（6）多渠道打包方式
	
	（7）有没有开发过gradle
	
	（8）解析xml和解析json有什么区别，为什么要用json

---

 **广州禾多云力有限公司**

没有笔试，直接Android技术人员问话形式，先自我介绍。
	
（1）对手机推流和FFmpeg打包的了解

（2）rtmp和rtsp的区别和优缺点

（3）怎样优化内存

（4）怎样提升性能

（5）怎样处理大图片

（6）广播的注册方式有哪几种

（7）进程有哪几种

（8）**Fragment的使用好处**

（9）**Fragment的生命周期有哪些**

（10）**Activity的启动方式有哪几种，分别是怎么样的**

（11）**构造者模式的使用好处**

（12）单例模式的使用和**volatile关键字实现单例**

（13）ButterKnife和EventBus的工作原理是怎样的

（14）Android的事件分发机制是怎么样的

（15）**内容提供者怎么实现给别的应用使用**

（16）**强引用、软引用、弱引用、虚引用的场景，为什么用弱引用**

（17）handler、looper的关系和在子线程怎么实现handler机制

（18）**MVP和MVVM的区别和各自的优缺点**

（19）如何自定义View

（20）**熟不熟悉RxJava**

---

**广州爱拍游戏网络公司**

	有面试题，如下：
	
	（1）为什么不推荐使用枚举类型，请说明详细理由？
		
		答：编译器编译枚举类型时会导致DEX code的增加，运行时的内存分配占用过多，比静态常态占用的内存超过两倍之多，原因是枚举编译完之后，生成一个
		values() 数组，静态占用的内存就是对象本身的大小加上对象成员指向的其他对象大小。谷歌官方不建议使用枚举类型，如果在意内存优化这一方面。
	
	（2）view测量中，measure_spec有哪几种测量模式？
	
		答：测量模式是由父容器传递给子容器的，并不是对子容器的布局要求，而是由测量模式和view的layoutParameter共同测量。
		- UNSPECIFIED:父容器对子容器没有限制，子容器想多大就多大。
		
		- EXACTLY:父容器对子容器设置了具体的尺寸大小，子容器应该服从这些边界，不论它要多大。（match_parent、10dp)
	
		- AT_MOST:子容器可以是声明大小的任意大小（wrap_content)
	
	（3）算法题，计算D点到各点的最短距离。
		
		答：最短路径算法。参考广度优先算法和深度优先算法。
	
	（4）Sqlite数据库需要插入大量数据，有什么方法可以提高执行效率？
	
		答：主要方法为显式的开启事务，把所有数据的insert操作作为一个事务操作，减少磁盘操作次数，避免频繁的进行I/O操作，因为没有显式开启事务之前，
		每插入一条数据都会与磁盘进行读写，浪费大量时间。具体代码如下：
		String[] column = {
	            Config.COLUMN_USERID,
	    };
	    String selection = "UserID = '" + userID + "'";
	    Cursor cursor = database.query(Config.TABLE_R_USER_QUESTION_DO_HISTORY, column, selection, null, null, null, null);
		if(cursor.getCount == 0){
			//初始化表
			int rowNum = getTotalRowTable(Config.TABLE_R_USER_QUESTION_DO_HISTORY);
			//显式的开启事务
			dataBase.beginTransaction();
	
			try{
				ContentValues values = new ContentValues();
	            for (int i = 0; i < 100000; i++) {
	                values.put(Config.COLUMN_USERID, userID);
	                rowNum = ++rowNum;
	                values.put(Config.COLUMN_QUESTIONNUM, rowNum + "");
	                values.put(Config.COLUMN_ISCOLLECTED, 0);
	                values.put(Config.COLUMN_WRONGTIMES, 0);
	                values.put(Config.COLUMN_RIGHTTIMES, 0);
	                database.insert(Config.TABLE_R_USER_QUESTION_DO_HISTORY, null, values);
	            }			
				//设置事务的标志为成功
				dataBase.setTransactionSuccessful();
			}catch(Exception e){
				throw e;
			}finally{	//进行相关的资源关闭
				cursor.close();
				dataBase.endTransaction();
				dataBase.close();
			}
		}
	
	（5）Handler、Looper、MessageQueue三者的关系。
	
		答：Handler负责发送Message和处理Message。
	
		   Looper是负责从消息队列（MessageQueue）中不断的调用loop方法查询Message，如果有Message则交给message的target(Handler)的
		   dispatchMessage去处理，没有就一直等待（阻塞），每个线程都 维护着唯一的一个Looper，每个Looper都会初始化一个MessageQueue,
		   之后进入一个无线循环一直读取MessageQueue的消息，没有消息就一直阻塞。
	
		   MessageQueue就是一个消息队列，以单链表的数据结构来存储消息列表，以队列的形式提供插入和删除操作（单链表在插入和删除操作上效率比较高），
		   next方法是一个无限循环的方法，如果消息队列中没有消息，那么next方法会一直堵塞，有新消息的事后，next方法会返回这条消息并从链表中
		   删除该消息。
	
		   	应用启动时会在ActivityThread的main函数中会调用Looper.prepareMainLooper()方法为主线程实例化一个Looper，然后调用Looper.loop(),
			所以Handler在主线程中可以直接new Handler（）,构造函数不用传递Looper。
			下面是在子线程如何初始化Handler机制:
			new Thread(new Runnable(){
				@Override
				public void run(){
					Looper.prepare();	//必须先调用此方法初始化当前线程的Looper实例
					
					Handler subThreadHandler = new Handler(Looper.myLooper()){
						@Override
						public void handlerMessage(Message msg){
						
						}
					};
	
					Looper.loop();	//必须调用此方法开启循环机制去MessageQueue获取消息
				}
			}）.start();
			
	(6)如何优化内存，避免内存溢出和内存泄漏？
	
		答：优化内存也就是优化应用占整个系统的内存。（主要从以下几个大方面考虑：分配对象、view层级、数据优化、dex优化代码优化）
			1、避免创建不需要的对象。对于生命周期较短的临时变量，尽量想办法规避掉每次都要去创建它，这样GC回收被强制调用机会就会更少，留给Android
			系统进行UI渲染或者音频加载的时间就会更多，从而避免了卡顿现象。
	
			2、尽量使用一些轻量级容器存储数据，比如用SparseArray代替HashMap，因为当HashMap的负载因子达到0.75时，会进行双倍扩容，不做空间整理，
			内存使用率低，适用于数据量较大或者内存空间相对宽裕。SparseArray避免了key的自动装箱操作（但key一定是iInteger、Long类型）、空间压缩，
			进行矩阵压缩大大减少了存储空间，节约内存，但增、查、删速度相对较慢，适用于数据量较小的场景下。
	
			3、尽量避免使用枚举Enum而选择用静态常量，编译器编译枚举类型时会生成大量代码和对象，每个枚举变量对应一个数组，占用的内存是静态常量的两倍
			
			4、可以适当重写onTrimMemory回调方法，可以在这个方法内检测到系统内存是否不足，然后通过处理部分资源来释放内存，从而避免应用被系统杀死。
			
			5、当使用Service应当小心小心再小心！当你需要启动一个服务在后台执行一项任务时，应当在其完成工作之后尽快的停止此服务。可以考虑使用
			IntentService—当在子线程完成耗时操作之后，IntentService会自动停止并结束自身。然而在实际开发中经常会碰到需要服务去执行一项耗时比较长
			的任务，比如:音乐播放器，下载APP等等。像这样的应用可以分隔为两个进程:一个进程负责 UI 工作, 另外一个则在后台服务中运行其它的工作. 在
			AndroidManifest 文件中为各个组件申明 android:process 属性就可以分隔为不同的进程。注意一点:在后台运行的Service绝对不能处理或者持有
			任何UI，否则系统可能会分配双倍甚至三倍的空间来维护UI资源！！（ps:service启动的两种方式：第一种是bindService,当退出应用时service会
			自动停止销毁，第二种是startService，即使退出应用后也会继续在后台运行）
	
			6、ListView在getView方法中尽量复用convertView，因为在getView中会频繁调用而生成一些重复的对象。
			
			如何避免内存溢出？（主要从三个方面：内存引用，图片处理，分配内存，释放资源）
			1、释放强引用，使用软引用或者弱引用。
			2、图片压缩处理，质量压缩和比例压缩。
			3、分配足够的内存大小，不建议，可能会通过杀死其他后台程序获取可以分配的内存。
			4、数据库、IO流用完记得关闭，广播和服务组件随着Activity的销毁而注销。
			5、避免内部类持有外部类的引用。

[内存溢出和内存泄漏的概念](https://blog.csdn.net/alionsss/article/details/53966809)	

[静态变量什么时候被分配和回收](http://blog.sina.com.cn/s/blog_476d58ef0102vz2n.html)	

[HashMap的实现原理](https://blog.csdn.net/yanlove_jing/article/details/51637766)

[HashMap、ArrayMap、SparseArray的比较](https://blog.csdn.net/chen_lifeng/article/details/52057427)

[SparseArray的实现原理](https://blog.csdn.net/chen_lifeng/article/details/52001865)

[位运算符](https://blog.csdn.net/qq_35114086/article/details/70173329)

[IntentService详解](http://blog.qiji.tech/archives/2693)
	
[service详解](https://blog.csdn.net/javazejian/article/details/52709857)

[ListView和RecyclearView的区别](https://www.jianshu.com/p/f011fa974fbe)
	
	(7)进程间通信方式有哪些？
	
	**信号、信号量、消息队列、管道、共享内存、socket**	
	
	[参考链接](https://www.cnblogs.com/Jason-Jan/p/8459687.html)
	
		答：1、（管道通信）使用Intent:Activity，Service，Receiver，ContentProvider都支持在Intent中传递Bundle数据，而bundle实现了
		Parcelable接口，可以在进程间传输。在一个进程中启动了另一个进程的Activity，Service，Receiver，ContentProvider,可以在Bundle中附加要
		传递的数据通过Intent发出去。
	
		优点：简单方便，可以实现任意关系的进程间的通信		缺点：局限于单向通信，只能创建在它的进程以及其有亲缘关系的进程之间，只适用于传递少量数据
		
		2、（共享内存）使用文件共享：
		（1）可以在一个进程中序列化一个对象到文件系统中，在另一个进程中反序列化恢复这个对象（注意：并不是同一个对象，只是内容相同。）
		（2）Windows 上，一个文件如果被加了排斥锁会导致其他线程无法对其进行访问，包括读和写；而 Android 系统基于 Linux ，使得其并发读取文件没有
		限制地进行，甚至允许两个线程同时对一个文件进行读写操作，尽管这样可能会出问题。
		（3）SharedPreferences 是个特例，系统对它的读 / 写有一定的缓存策略，即内存中会有一份 ShardPreferences 文件的缓存，系统对他的读 / 写就
		变得不可靠，当面对高并发的读写访问，SharedPreferences 有很多大的几率丢失数据。因此，IPC 不建议采用 SharedPreferences。
		
		优点：无须复制，快捷，信息量大。	 
		缺点：进程间的读写操作的同步问题，利用内存缓冲区直接交换信息，内存的实体存在于计算机中，只能同一个计算机系统中的诸多进程共享，不方便网络通信
		
		3、（Binder通信机制）使用AIDL：
		 Messenger 本质上也是 AIDL ，只不过系统做了封装方便上层的调用而已，以串行方式处理客户端发来的消息，大量并发请求处理不了，并且 Messenger 
		只适合传递消息，不能跨进程调用服务端的方法。
		
		优点：可以处理大量并发请求，可以夸进程调用服务端的方法。	缺点：操作繁杂，生成AIDL文件。
	
		4、（套接字）使用socket（面向网络的进程间通信方式）
		网络的 Socket 数据传输是一种特殊的 I/O，Socket 也是一种文件描述符。Socket 也具有一个类似于打开文件的函数调用： Socket()，该函数返回一个
		整型的Socket 描述符，随后的连接建立、数据传输等操作都是通过该 Socket 实现的。
		
		优点：只要有网络存在，跨越任何限制，数据量大，快速。	缺点：受网络带宽限制和延时影响。
	
	（8）Activity、Window和View之间的关系。
		
	[参考链接](https://www.jianshu.com/p/a533467f5af5)
	
		答：Activity像一个工匠（整个模型控制单元），Window像窗户（承载模型），View像窗花（视图显示模型）。
		Activity的启动是通过ActivityThread的main函数调用其attach方法，最终是在performLaunchActivity方法中通过类加载器构建Activity
		实例，然后Activity调用attach方法创建该Activity所属的Window对象并为其设置回调接口，而View是Android中的视图呈现方式，不能单独存在
		必须依附于Window这个抽象的概念上，View是通过ViewRoot绑定到Window上，在ActivityThread中，当Activity对象被创建完毕后，会将
		DecorVidew添加到Window中，同时会创建ViewRootImpl对象，并将ViewRootImpl对象和DecorView建立关联。
	
	（9）怎么优化布局？

---

**三七互娱**

没有面试，自我介绍。四个人员面试。

技术人员：

- 开始项目怎么分模块
- 怎么用MVP，可以画图解释（通过接口注入和接口回调，目的是解耦，减少view层次的代码冗余，优点是方便测试，缺点是维护成本）
- 说一下认为做的最好的项目
- 看过哪些Android源码（回答事件分发）
- 如果想事件不往下分发，应该怎么处理？(interrupt返回true,onTouchEvent返回true)
- git的使用
- 对加班制度怎么看
- 有什么问题想问的

技术总监：

- 为什么离职（个人发展、技术提升）
- 技术是想往哪方面提升？
- 对SDK感兴趣吗？
- 自己掌握的知识体系有哪些？
- Android线下产品跟线上淘宝的那些有什么不同呢？
- 对以后得发展规划怎么看待？
- 对加班怎么看？

人事HR：

- 离职原因？
- 对工作时间有什么要求吗？
- 上一家的薪资和目前期望的薪资多少？
- 对这岗位是否感兴趣？
- 对三七互娱的了解

技术部负责人：

- 怎么解决一个问题？（首先判断是否是自己APP这边问题，然后再反馈给对接人员）
- 对项目进度的时间是怎么安排的？



	