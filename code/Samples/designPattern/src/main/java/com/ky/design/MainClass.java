package com.ky.design;

public class MainClass {

    public static void main(String... arg) {
        System.out.println("main");
        System.out.println("currentThread id = " + Thread.currentThread().getId());

        System.out.println("ԭ��ģʽ������������");
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

        //master head >>>
    }
}
