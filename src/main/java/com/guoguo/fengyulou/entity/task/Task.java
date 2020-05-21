package com.guoguo.fengyulou.entity.task;

import com.guoguo.common.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 任务
 */
@Data
public class Task extends BaseEntity {
    private Long id;
    /**
     * 项目id
     */
    private Long projectId;
    /**
     * 任务简述
     */
    private String sketch;
    /**
     * 任务标签id
     */
    private Long taskLabelId;
    /**
     * 任务状态 0未完成 1已完成
     */
    private Integer status;
    /**
     * 完成时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date finishTime;
    /**
     * 人员id
     */
    private Long memberId;
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 任务标签名称
     */
    private String taskLabelName;
    /**
     * 人员姓名
     */
    private String memberName;

}
