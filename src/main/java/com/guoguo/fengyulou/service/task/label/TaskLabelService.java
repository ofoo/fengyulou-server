package com.guoguo.fengyulou.service.task.label;

import com.github.pagehelper.PageInfo;
import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.entity.task.label.TaskLabel;

import java.util.List;

public interface TaskLabelService {
    /**
     * 按条件查询
     *
     * @param taskLabel
     * @return
     */
    List<TaskLabel> getTaskLabelList(TaskLabel taskLabel);

    /**
     * 按条件分页查询
     *
     * @param taskLabel
     * @return
     */
    PageInfo<TaskLabel> getTaskLabelListPage(TaskLabel taskLabel);

    /**
     * 保存数据
     *
     * @param taskLabel
     * @return
     */
    ServerResponse saveTaskLabel(TaskLabel taskLabel);

    /**
     * 按id和用户id查询
     *
     * @param taskLabel
     * @return
     */
    TaskLabel getTaskLabelByIdAndUserId(TaskLabel taskLabel);

    /**
     * 按id和用户id删除
     *
     * @param ids
     * @param userId
     * @return
     */
    ServerResponse deleteTaskLabelByIdsAndUserId(List<Long> ids, Long userId);
}
