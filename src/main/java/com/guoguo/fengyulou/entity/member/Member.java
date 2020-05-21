package com.guoguo.fengyulou.entity.member;

import com.guoguo.common.BaseEntity;
import lombok.Data;

/**
 * 人员
 */
@Data
public class Member extends BaseEntity {
    private Long id;
    /**
     * 人员姓名
     */
    private String name;
    /**
     * 人员标签id
     */
    private Long memberLabelId;
    /**
     * 人员手机号
     */
    private String mobile;
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 人员标签名称
     */
    private String memberLabelName;
}
