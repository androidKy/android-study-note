package com.ky.test.thread;

/**
 * description:
 * author:kyXiao
 * date:2020/4/8
 */
public class ThreadTest {

    public static void main(String[] args) {
        Thread previousThread = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            JoinDemo joinDemo = new JoinDemo(previousThread, i);
            joinDemo.start();
            previousThread = joinDemo;
            System.out.println("main thread index:" + i);
        }
    }

    public static class JoinDemo extends Thread {
        int i;
        Thread previousThread; //上一个线程

        public JoinDemo(Thread previousThread, int i) {
            this.previousThread = previousThread;
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println("join called before num:" + i);
            try {
                //调用上一个线程的join方法，大家可以自己演示的时候可以把这行代码注释掉
                previousThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("join called after num:" + i);
        }

    }
}



