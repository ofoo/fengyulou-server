package com.guoguo.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
    /**
     * 设置cookie值
     * @param name 名称
     * @param value 值
     * @param time 时间（秒）
     * @return
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int time){
        Cookie cookie = new Cookie(name,value);
        cookie.setMaxAge(time);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
