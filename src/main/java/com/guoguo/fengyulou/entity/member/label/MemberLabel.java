package com.guoguo.fengyulou.entity.member.label;

import com.guoguo.common.BaseEntity;
import lombok.Data;

/**
 * 人员列表
 */
@Data
public class MemberLabel extends BaseEntity {
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
