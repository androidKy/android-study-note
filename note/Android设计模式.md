[TOC]

# Android设计模式

## 什么是设计模式？

设计模式是一套被反复使用、多数人知晓的、经过分类的、代码设计经验的总结。（百度百科）

简单来说就是为了让代码解耦，提高执行效率。

## 设计模式作用？

为了让代码可重用，让代码更容易被他人使用，保证代码可靠性、扩展性，降低耦合。

## 设计原则？

1. ***单一职责原则***：对于一个类而言，应该仅有一个引起它变化的原因。例如：一个需求是实现图片加载并且要把图片缓存起来，可以拆分成一个图片加载类，一个缓存类，一个压缩类。

2. ***开闭原则***：对于扩展是开放的，但对于修改是关闭的。尽量通过扩展的方式来实现变化，而不是通过修改。例如：一个图片的缓存方式。

3. ***里氏替换原则***：所有引用父类的地方必须能透明地使用其子类的对象。例如：Android源码的Window类和View类还有View的继承类。Window依赖于View类，View定义一个视图抽象，具体实现交给子类。

   ```java
   //窗口类
   public class Window{
       public void show(View child)
       {
           child.draw();	//会调用View的子类的draw方法
       }
   }
   //建立视图抽象，测量视图的宽高为公用代码，绘制实现交给具体的子类
   public abstract class View{
       public abstract void draw();
       public void measure(int width,int height){}
   }
   //按钮类的具体实现
   public class Button extends View{
       @Override
       public void draw() {
           //绘制按钮
       }
   }
   //TextView的具体实现
   public class TextView extends View{
       @Override
       public void draw(){
           //绘制文本
       }
   }
   ```

4. ***依赖倒置原则***：简单来说就是面向接口编程。模块间的依赖通过抽象产生，实现类之间不发生直接的依赖关系，其依赖关系是通过接口或者抽象类发生。

   - （1）高层模块不应该依赖于底层模块，两者都依赖于抽象；

   * （2）抽象不应该依赖于细节；

   * （3）细节应该依赖抽象。

     (注意：抽象是指不能直接实例化，比如接口和抽象类，细节是指实现类，可以直接实例化。高层模块就是调用端，底层模块就是实现类）

   ```java
   public class ImageLoader{
       //MemoryCache mCache = new MemoryCache();	//ImageLoader依赖于MemoryCache这个实现类，当MemoryCache不能满足需求时需要修改ImageLoader里面的代码
       ImageCache mCache = new MemoryCache();	//依赖于接口ImageCache
       
       public void setImageCache(ImageCache imageCache)
       {
           mCache = imageCache;
       }
   }
   public interface ImageCache{
       public Bitmap get(String url);
       public void put(Bitmap bitmap);
   }
   public MemoryCache implements ImageCache{
       @Override
       public Bitmap get(String url)
       {
           //具体实现
           return null;
       }
       
       @Override
       public void put(Bitmap bitmap)
       {
           //具体实现
       }
   }
   ```

5. ***接口隔离原则***：客户端不应该依赖它不需要的接口，类间的依赖关系应该建立在最小的接口上。目的是使系统解开耦合，从而容易重构、更改和重新部署。

   ```java
   public void put(String url,Bitmap bitmap)
   {
       FileOutputStream fileOutputStream = null;
       try{
           fileOutputStream = new FileOutputStream(cacheDir+url);
           bitmap.compress(CompressFormat.PNG,100,fileOutputStream);
       }catch(FileNotFoundException e)
       {
          e.printStackTrace();
       }finally{
          /*  if(fileOutputStream != null)
           {
               try{
                   fileOutputStream.close();
               }cache(IOException e)
               {
                   e.printStackTrace();
               }
           }
           */
          	//依赖于Closeable抽象，并且将接口最小化隔离
   		CloseUtils.closeQuietly(fileOutputStream);
       }
   }
   
   public final class CloseUtils{
       private CloseUtils(){}
       
       public static void closeQuietly(Cloaseable closeable)
       {
           if(null != closeable)
           {
               try{
                   closeable.close();
               }catch(IOException e)
               {
                   e.printStackTrace();
               }
           }
       }
   }
   ```

6. ***迪米特原则***：一个对象应该对其他对象有最少的了解。降低耦合度。

总结：在应用开发的过程中，要时时刻刻为后续的升级、维护过程中让应用能够拥抱变化做准备。


## 1.单例模式

### 1.1 定义

在程序生命期中只存在一个实例，将构造函数私有化，不能通过new创建实例对象。

### 1.2 作用

避免对常用的对象频繁创建从而节约程序分配资源。	

### 1.3 用法

#### 1.3.1懒汉式

懒汉式：顾名思义就是懒，在方法内部才new实例。

优点：只有在使用时才会被实例化，在一定程度上节约资源。

缺点：第一次加载时需要及时进行实例化，反应稍慢，每次调用getInstance都进行同步，造成不必要的同步开销。

```java
public class Single{
    private static volatile Single mInstance;	//volatile重排列
    
    private Single(){}
    
   	public static synchronized Single getInstance()	//synchronized保证在多线程情况下单例对象唯一
    {	
        if(mInstance == null)
			mInstance = new Single();
        return mInstance; 
    }
}
```

#### 1.3.2 饿汉式

饿汉式：在变量直接new实例。

优点：第一次加载反应快。	缺点：该实例对象不会被回收，直到程序被销毁，占用资源。

```java
public class Single{
    private static volatile Single mInstance = new Single();
    
    private Single(){}
    
    public static Single getInstance(){
        return mInstance;
    }
}
```

#### 1.3.3 静态内部类单例（推荐使用）

利用一个静态内部类保证程序整个生命周期中只有一个实例，因为静态实例的生命周期只有在类别卸载的时候才结束也就是程序销毁的时候。

优点：第一加载Single类时并不会初始化实例，只有在第一次调用getInstance方法才会初始化。	

缺点：单例对象一旦被创建后会一直存在，不会被回收。

```java
public class Single{
    private static class SingleHolder{
        private static final Single mInstance = new Single(); 
    }
    
    private Single(){}
    
    public static Single getInstance()
    {
        return SingleHolder.mInstance;
    }
}
```

#### 1.3.4 双重锁检验（不推荐使用）

优点：既能在需要时才初始化实例，又能够保证线程安全，实例化对象后再调用getInstance不进行同步锁。解决了资源消耗，多余的同步、线程安全等问题。

缺点：在某些情况下出现失效的问题，称为双重检查锁定失效。

```java
public class Single{
    private static final volatile Single mInstance = null;
    
    private Single(){}
    
    public static Single getInstance()
    {
        if(mInstance == null)
        {
            synchronized(Single.class)
            {
                if(mInstance == null)
                    mInstance = new Single();
            }
        }
        return mInstance;
    }
}
```



#### 1.3.5 枚举单例

优点：上述实现方式在反序列化情况下会出现重新创建对象的情况。通过序列化将一个单例的实例对象读写到磁盘，然后再读回来，反序列化时会通过特殊路径（相当于调用构造函数）创建一个新的实例。要想避免这种情况可以加入readResolve()方法（private Object readResolve() throw ObjectStreamException{ return mInstance; })。枚举即使反序列化也不会重新生成新的实例。

```java
public enum Single{
    INSTANCE;
}
```

#### 1.3.6 使用容器实现单例模式

优点：既能在需要时才初始化实例，又能够保证线程安全，且初始化后调用getInstance不进行同步锁。

```java
public class SingleManager{
    private static Map<String,Object> objMap = new HashMap<String,Object>;
    
    private SingleManager{}
    
  	public static void registerObj(String key,Object obj)
    {
        if(!objMap.containsKey(key))
        {
            objMap.put(key,obj);
        }
    }
    
    public static Object getObj(String key)
    {
        return objMap.get(key);
    }
}
```

### 1.4 使用场景

创建一个对象需要消耗的资源过多时，比如线程池创建、LayoutInflate、访问IO和数据库等资源时，就要考虑用单例模式。

## 2.构建者模式

### 2.1 定义

将一个对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。

### 2.2 作用

将构建复杂对象的过程和它的部件解耦，使得构建过程和部件的表示隔离开来。一个复杂的对象有很多大量组成的部分，例如一辆汽车，有车轮、方向盘、发动机等各种小零件，如何装配这个过程很漫长，也很复杂，为了在构建过程中对外部隐藏实现细节，就可以使用Builder模式将部件和组装过程分离。

### 2.3 用法

```java
public class AlertDialog{
    public static class Builder{
        
        public Builder(Context context){
            //...此处省略
        }
        
        private String title;
        private String content;
        private OnClickListener onPositiveClickListener;
        private OnClickListener onNegativeClickListener;
        
        public Builder setTitle(String title)
        {
            this.title = title;
            return this;
        }
        ...//其他set方法如setTitle
        
        public AlertDialog create(){
            View view = LayoutInflater.from(context).inflate(R.layout.view_id,null,false);
            TextView tvTitle = (TextView)view.findViewById(R.id.tv_title);
            tvTitle.setText(title);
            ...//具体细节
            
            AlertDialog dialog = new AlertDialog(context,theme);
            dialog.setContentView(view);
            return dialog;
        }
    }
}
```

### 2.4 使用场景

（1）相同的方法，不同的执行顺序，产生不同的事件结果时。

（2）一个对象特别复杂，参数很多时。

例如Android系统源码的AlertDialog类。

## 3.***观察者模式***

### 3.1 定义

定义对象间一种一对多的依赖关系，使得每当一个对象改变状态，则所有依赖于它的对象都会得到通知并被自动更新。

### 3.2 作用

解决类与类之间有逻辑交互时存在的耦合度。

### 3.3 用法

```java
//抽象类观察者
public abstract class Observer{
    public void update(String msg);
}
//抽象类被观察者
public abstract class Observable{
    public void registerObserver(Observer observer);
}
//观察者实现类
public class ObserverImpl extends Observer{
    @Override
    public void update(String msg){}
}
//被观察者实现类
public class ObservableImpl extends Observable{
    private List<Observer> mObserverList;
    
    @Override
    public void registerObserver(Observer observer)
    {
        if(mObserverList == null)
        {
            mObserverList = new ArrayList<>();
        }
        if(!mObserverList.contains(observer))
            mObserverList.add(observer);
    }
    
    public void updateMsg(String msg){
        notifyObserver(msg);
    }
    
    private void notifyObserver(String msg){
        if(mObserverList == null) return;
        for(int i = 0; i < mObserverList.size; i+=)
        {
            mObserverList.get(i).update(msg);
        }
    }
}

public class Test{
    public static void main(String[] args)
    {
        Observer observerImplA = new ObserverImpl();
        Observer observerImplB = new ObserverImpl();
        
        Observable observableImpl = new ObservableImpl();
        observableImpl.registerObserver(observerImplA);
        observableImpl.registerObserver(observerImplB);
        observableImpl.updateMsg("数据发生变化");
    }
}
```

### 3.4 使用场景

* 关联行为场景，并且关联行为是可拆分的，而不是 **组合** 关系。

* 事件多级触发场景。

* 跨双系统的消息交换场景，如消息队列、事件总线的处理机制。

  例如：EventBus事件总线、RxJava、BroadCast等。

## 4.***代理模式***

### 4.1 定义

给某一个对象提供一个代理对象，并由代理对象控制原对象的引用。

### 4.2 作用

给对象增加了本地化的扩展，增加了存取操作控制。但缺点是产生多余的代理类。

### 4.3 用法

* 角色介绍
  * 抽象对象角色：声明了目标对象和代理对象的共同接口，可以在任何使用目标对象的地方都可以使用代理对象。
  * 目标对象角色：定义了代理对象所代表的目标对象。
  * 代理对象角色：代理对象内部含有目标对象的引用，可以在任何时候操作目标对象。

* 示例代码

  ```java
  public interface ICommon{
      public void proxyMethod();
  }
  
  public class TargetObj implements ICommon{
      @Override
      public void proxyMethod(){
          //共同的接口
      }
  }
  
  public class ProxyObj implements ICommon{
  	//init targetObj method by constructor
      private ICommon common;
    
      public ProxyObj(ICommon common){
          this.common = common;
      }
      //init targetObj directly
      //private ICommon common = new TargetObj();
      
      @Override
      public void proxyMethod(){
          //此处可以处理一些未调用目标对象的逻辑
          
          //此处调用目标对象的接口
  		if(common != null)
          {
              cmmon.proxyMethod();
          }
          //此处可以处理一些调用目标对象之后的逻辑
      }
  }
  ```

#### 4.4 使用场景

* 当不能直接对目标对象直接操作引用时。
* 实例有Android源码中的Binder夸进程通信机制和AIDL

## 5.装饰(包装)模式

### 5.1 定义

是指在不必改变原类文件和使用继承的情况下，动态地扩展一个对象的功能。它是通过创建一个包装对象，也就是装饰来包裹真实的对象。 

### 5.2 作用

是继承关系的一种替代方案之一，就增加功能来说，装饰模式比生成子类更为灵活。

### 5.3 用法

创建一个包装对象来包裹真实的对象，在不改变ComponentA类的情况下扩展功能。

```java
//抽象组件
public interface Component{
    public void operate();
}
//组件具体实现类
public class ComponentA extends Component{
    @Override
    public void operate(){
        //A的具体实现
    }
}
//抽象包装类
public abstract Decorator extends Component{
    
    private Component mComponent;
    
    public Decorator(Component component)
    {
        this.mComponent = component;
    }
    
    @Override
    public void operate(){
        component.operate();
    }
}
//具体包装类
public class DecoratorA extends Decorator{
    protected DecoratorA(Component component)
    {
        super(component);
    }
    @Override
    public void operate()
    {
        operateA();	//新增的功能
		super.operate();
        operateB();	//新增的功能
    }
    
    private void operateA(){}
    
    private void operateB(){}
}
//调用
class Client{
    public static void main(Strings... args){
        Component componentA = new ComponentA();
        Decorator decoratorComponent = new DecoratorA(componentA);
        decoratorComponent.operate();
    }
}
```

### 5.4 使用场景

（1）需要扩展一个类的功能，但不能修改该类的代码。

（2）当不能使用生成子类的方法进行扩充时。

例如：java IO流是典型的装饰模式。

## 6.工厂方法模式

### 6.1 定义

定义一个用于创建对象的接口，让子类决定实例化哪个类。PS:是简单工厂模式的扩展。

### 6.2 作用

通过依赖注入以达到解耦、复用和方便后期维护拓展的目的 。

### 6.3 用法

* 角色关系

  * 抽象工厂：工厂方法模式的核心，定义创建对象的统一接口。
  * 抽象产品：需要创建对象的父类，定义产品的一些通用接口。
  * 具体工厂：实现或者继承抽象工厂的子类，实现具体的业务逻辑，创建具体的对象。
  * 具体产品：实现或者继承抽象产品的子类，某个具体产品的对象。

* 实例代码

  ```java
  public abstract class Factory{
      public abstract Product createProduct();
  }
  
  public abstract class Product{
      public abstract void method(){};
  }
  
  public class ProductA extends Product{
      @Override
      public void method(){
          
      }
  }
  
  public class ProductB extends Product{
      @Override
      public void method(){
          
      }
  }
  
  public class FactoryA extends Factory{
      @Override
      public Product createProduct(){
          return new ProductA();
      }
  }
  
  public class FactoryB extends Factory{
      @Override
      public Product createProduct(){
          return new ProductB();
      }
  }
  
  public static void main(Strings... args)
  {
      Factory factoryA = new FactoryA();
      ProductA productA = factoryA.createProduct();
      productA.method();
      
      Factory factoryB = new FactoryB();
      ProductB productB = factoryB.createProduct();
      productB.method();
  }
  ```

### 6.4 使用场景

* 在任何需要生成复杂对象的地方。
* 如果用new就可以完成创建的对象无需使用。
* 适用于项目比较大的情况，如果项目较小可以用简单工厂模式。

## 7.抽象工厂模式

### 7.1 定义 

> 提供一个创建一系列相关或互相依赖对象的接口，而无需指定它们具体的类。基于工厂方法模式。

### 7.2 作用

* 易于交换产品系列。
* 让具体的创建实例过程与客户端分离。

### 7.3 用法

基本和工厂方法模式一样，只是抽象工厂模式能生产多种产品，工厂方法模式只能生产一种产品。

如果项目较小，可以用 **反射机制+配置文件+简单工厂模式** 改进代码。

### 7.4 使用场景

* 适用于项目比较大，需要生产多种产品。

## 8.***适配器模式***

### 8.1 定义

> 把一个类的接口变成客户端所期待的另一种接口，从而使原本因接口不匹配而无法一起工作的两个类能够在一起工作。

### 8.2 作用

将两个不兼容的类融合在一起，使他们能正常工作。

### 8.3 用法

适配模式分两种：类适配器模式和对象适配器模式。

* 类适配器模式：用电源接口做例子，笔记本电脑的电源一般是5V，生活中的电源是220V。（转接口）

  ~~~java
  public interface FiveVolt{
      public int getVolt5();
  }
  public class Volt220{
      public int getVolt220(){
          return 220;
      }
  }
  
  public class VoltAdapter extends Volt220 implements FiveVolt{
      @Override
      public int getVolt5(){
          return 5;
      }
  }
  
  public class Test{
      public static void main(String[] args)
      {
         	VoltAdapter adapter = new VoltAdapter();
      	int volt5 = adapter.getVolt5();
      	int volt220 = adapter.getVolt220(); 
      }
  }
  ~~~

* 对象适配器模式：

  ~~~java
  public interface FiveVolt{
      public int getVolt5();
  }
  public class Volt220{
      public int getVolt220(){
          return 220;
      }
  }
  public class VoltAdapter implements FiveVolt{
      private Volt220 volt220;
      
      public VoltAdapter(Volt220 volt220){
          this.volt220 = volt220;
      }
      
      @Override
      public int getVolt5(){
          return 5;
      }
      
      public int getVolt220(){
          if(volt220 != null)
              return volt220.getVolt220();
          return 0;
      }
  }
  ~~~

### 8.4 使用场景

* ListView和RecyclerView的adapter就是经典的对象适配器模式的优秀实例。
* 想要建立一个可以重复使用的类，用于与彼此之间没有太大关联的一些类，包括一些可能在将来引进的类一起工作。
* 需要一个统一的输出接口，而输入端的类型不可预知。

### ListView和RecyclerView的比较:

1. RecyclerView可通过设置LayoutManager实现布局高度可定制化，横、竖、瀑布流。
2. RecyclerView不用通过手动判断convertview是否为空从而循环使用view资源。
3. ListView可以设置setOnItemListener监听item选项的点击事件，RecyclerView没有，需要自己手动添加。
4. ListView需要手动添加Header和footer，RecyclerView可以根据getViewType方法返回的值判断item选项类型

## 9.原型模式

### 9.1 定义

> 用原型实例指定创建对象的种类，并且通过复制这些原型创建新的对象。

### 9.2 作用

* 如果创建新的对象比较复杂时，可以利用原型模式简化对象的创建过程，同时也能够提高效率。
* 可以使用深克隆保持对象的状态。
* 原型模式提供了简化的创建结构。

### 9.3 用法

1. 原型类必须实现Cloneable接口：该接口表示该类能够复制且具体复制的能力。
2. 重写clone方法：实现自己的拷贝逻辑。
3. 有浅拷贝和深拷贝两种方式：
   * **浅拷贝**：使用一个已知实例对新创建实例的成员变量逐个赋值。
   * **深拷贝**：不仅要复制对象的所有非引用成员变量值，还要为引用类型的成员变量创建新的实例，并且初始化为形式参数实例值。

```java
public class PrototypeObj implements Cloneable{
    private String member01;
    private String member02;
    
    @Override
    public PrototypeObj clone() {
        //深拷贝
        PrototypeObj obj = null;
        try{
            obj = (PrototypeObj) super.clone();
        }catch(CloneNotSupportedException e)
        {
        	e.printStackTrace();    
        }
        //浅拷贝
        try{
			obj = new PrototypeObj();
        	obj.member01 = this.member01;
        	obj.member02 = this.member02;
        }catch(CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
      
        return obj;
    }
}
public class MainClass{
     public static void main(String... arg) {
        System.out.println("原型模式》》》》》》");
        PrototypePattern prototypePattern = new PrototypePattern();
        try {
            PrototypePattern cloneObj = (PrototypePattern) prototypePattern.clone();

            System.out.println("cloneMember01 = " + cloneObj.getMember01());
            System.out.println("cloneMember02 = " + cloneObj.getMember02());
            System.out.println("prototypePattern == cloneObj ? " + (prototypePattern == 								cloneObj));	//一定不相等，不是同一个对象，对应的内存映射地址不一样
            System.out.println("prototypePattern.equals(cloneObj)? " + 											    (prototypePattern.equals(cloneObj)));//看equals方法
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
```

### 9.4 使用场景

* 如果创建新对象成本较大，我们可以利用已有的对象进行复制来获得。
* 如果系统要保存对象的状态，而对象的状态变化很小，或者对象本身占内存不大的时候，也可以使用原型模式配合备忘录模式来应用。相反，如果对象的状态变化很大，或者对象占用的内存很大，那么采用状态模式会比原型模式更好。 
* 需要避免使用分层次的工厂类来创建分层次的对象，并且类的实例对象只有一个或很少的几个组合状态，通过复制原型对象得到新实例可能比使用构造函数创建一个新实例更加方便。

## 10.策略模式



## 11.状态模式



## 12.责任链模式



## 13.解释器模式



## 14.命令模式



## 15.备忘录模式



## 16.迭代器模式



## 17.模板方法模式



## 18.访问者模式



## 19.中介者模式



## 20.组合模式



## 21.享元模式



## 22.外观模式



## 23.桥接模式









[^注意 ]: 