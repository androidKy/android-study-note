package com.ky.planj.util;

import java.util.Objects;

/**
 * description:
 * author: kyXiao
 * date: 2018/11/15
 */
public final class ObjectHelper {

    private ObjectHelper() {
        throw new IllegalStateException("No instances!");
    }

    public static Object requireNonNull(Object obj, String msg) {
        if (obj == null)
            throw new NullPointerException(msg);
        return obj;
    }


}
