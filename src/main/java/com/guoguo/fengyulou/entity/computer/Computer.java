package com.guoguo.fengyulou.entity.computer;

import com.guoguo.common.BaseEntity;
import lombok.Data;

/**
 * 云服务器
 */
@Data
public class Computer extends BaseEntity {
    private Long id;
    /**
     * 项目id
     */
    private Long projectId;
    /**
     * 主机
     */
    private String host;
    /**
     * 端口
     */
    private String port;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 项目名称
     */
    private String projectName;

}
