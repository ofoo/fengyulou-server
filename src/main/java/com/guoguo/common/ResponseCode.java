package com.guoguo.common;

/**
 * Created by geely
 */
public enum ResponseCode {

    SUCCESS(0,"操作成功"),
    ERROR(1,"操作失败"),
    NOT_LOGIN(2,"请登录"),
    EXIST(3,"数据已存在");

    private final int code;
    private final String desc;


    ResponseCode(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }

}
