package com.cmb.o2o.cat.rack.common;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by qiqing on 2019/11/29.
 */
public final class StaticMap {

    private static final ConcurrentHashMap<String,Object> M_MAP=new ConcurrentHashMap<>(64);

    public static void put(String key,Object value){
        M_MAP.put(key,value);
    }

    public static Object get(String key){
        return M_MAP.get(key);
    }
}
