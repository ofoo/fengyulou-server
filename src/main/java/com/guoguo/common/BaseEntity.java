package com.guoguo.common;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类公共属性
 */
@Data
public class BaseEntity implements Serializable {
    /**
     * 添加时间
     */
    private Date insertTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 修改时间
     */
    private Date deleteTime;
    /**
     * 0正常 1删除
     */
    private Integer delete;
    /**
     * 页码
     */
    private Integer pageNum;
    /**
     * 每页条数
     */
    private Integer pageSize;
}
