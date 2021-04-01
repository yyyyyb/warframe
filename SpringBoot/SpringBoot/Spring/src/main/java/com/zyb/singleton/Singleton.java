package com.zyb.singleton;

public class Singleton {
    public static volatile Singleton singleton = new Singleton();

    public Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
