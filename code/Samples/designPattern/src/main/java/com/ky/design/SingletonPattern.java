package com.ky.design;

/**
 * description: single pattern
 * author: kyXiao
 * date: 2018/12/7
 */
public class SingletonPattern {
    private static volatile SingletonPattern mInstance;

    private SingletonPattern() {

    }

    public static SingletonPattern getInstance() {
        if (mInstance == null) {
            synchronized (SingletonPattern.class) {
                if (mInstance == null)
                    mInstance = new SingletonPattern();
            }
        }
        return mInstance;
    }

}
