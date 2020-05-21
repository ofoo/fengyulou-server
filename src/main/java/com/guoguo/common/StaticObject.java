package com.guoguo.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 静态对象
 */
public class StaticObject {
    protected static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

}
