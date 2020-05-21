package com.guoguo.fengyulou.entity.project;

import com.guoguo.common.BaseEntity;
import lombok.Data;

/**
 * 项目
 */
@Data
public class Project extends BaseEntity {
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
