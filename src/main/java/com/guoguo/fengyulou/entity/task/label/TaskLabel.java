package com.guoguo.fengyulou.entity.task.label;

import com.guoguo.common.BaseEntity;
import lombok.Data;

/**
 * 任务标签
 */
@Data
public class TaskLabel extends BaseEntity {
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 用户id
     */
    private Long userId;
}
