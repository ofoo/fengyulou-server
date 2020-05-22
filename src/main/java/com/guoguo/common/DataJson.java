package com.guoguo.common;

import lombok.Data;

@Data
public class DataJson {
    /**
     * 状态0正常
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 总数据数
     */
    private Long count;
    /**
     * 数据集合
     */
    private Object data;

    public static DataJson list(String msg, Long count, Object data) {
        DataJson dataJson = new DataJson();
        dataJson.code = 0;
        dataJson.msg = msg;
        dataJson.count = count;
        dataJson.data = data;
        return dataJson;
    }

    public static DataJson list(Long count, Object data) {
        DataJson dataJson = new DataJson();
        dataJson.code = 0;
        dataJson.count = count;
        dataJson.data = data;
        return dataJson;
    }
}
