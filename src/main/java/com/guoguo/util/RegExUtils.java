package com.guoguo.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具
 */
public class RegExUtils {

    /**
     * 必须含有小写字母、大写字母、数字、特殊符号两种及以上
     */
    public static String NUMBER_LETTER_SYMBOL = "^(?![A-Z]+$)(?![a-z]+$)(?!\\d+$)(?![\\W_]+$)\\S{6,16}$";

    /**
     * 正则验证
     *
     * @param str
     * @return
     */
    public static boolean verify(String str, String regEx) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
