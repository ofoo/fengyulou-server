package com.guoguo.fengyulou.entity.user;

import com.guoguo.common.BaseEntity;
import lombok.Data;

/**
 * 用户
 */
@Data
public class User extends BaseEntity {
    private Long id;
    /**
     * 用户账号
     */
    private String loginName;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 用户类型 0普通用户 1管理员
     */
    private Integer type;
}
