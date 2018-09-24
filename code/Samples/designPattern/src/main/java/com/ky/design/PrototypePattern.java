package com.ky.design;

/**
 * description:原型模式
 * author: kyXiao
 * created date: 2018/9/21
 */

public class PrototypePattern implements Cloneable {
    private String member01 = "member01";
    private String member02 = "member02";

    @Override
    protected Object clone() throws CloneNotSupportedException {

        return super.clone();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        PrototypePattern cloneObj = null;
        if (obj instanceof PrototypePattern) {
            cloneObj = (PrototypePattern) obj;
        }
        if (cloneObj != null) {
            String member01 = cloneObj.getMember01();
            if (member01.equals(this.member01)) {
                return true;
            }
        }
        return super.equals(obj);
    }

    public String getMember01() {
        return member01;
    }

    public void setMember01(String member01) {
        this.member01 = member01;
    }

    public String getMember02() {
        return member02;
    }

    public void setMember02(String member02) {
        this.member02 = member02;
    }
}
