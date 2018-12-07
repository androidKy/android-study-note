package com.ky.design;

public class MainClass {

    private static SingletonPattern singletonPattern01;
    private static SingletonPattern singletonPattern02;

    public static void main(String... arg) {
        System.out.println("main");
        System.out.println("currentThread id = " + Thread.currentThread().getId());

        testSinglePattern();
    }

    private static void testPrototypePattern() {
        PrototypePattern prototypePattern = new PrototypePattern();
        try {
            PrototypePattern cloneObj = (PrototypePattern) prototypePattern.clone();

            System.out.println("cloneMember01 = " + cloneObj.getMember01());
            System.out.println("cloneMember02 = " + cloneObj.getMember02());
            System.out.println("prototypePattern == cloneObj ? " + (prototypePattern == cloneObj));
            System.out.println("prototypePattern class == cloneObj class ? " + (prototypePattern.getClass() == cloneObj.getClass()));
            System.out.println("prototypePattern.equals(cloneObj)? " + (prototypePattern.equals(cloneObj)));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    private static void testSinglePattern() {

        new Thread(() -> {
            singletonPattern01 = SingletonPattern.getInstance();

            System.out.println("singletonPattern01 : " + singletonPattern01.toString());
        }).start();

        new Thread(() -> {
            singletonPattern02 = SingletonPattern.getInstance();

            System.out.println("singletonPattern02 : " + singletonPattern02.toString());
        }).start();
    }
}
