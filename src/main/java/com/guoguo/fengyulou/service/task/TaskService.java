package com.guoguo.fengyulou.service.task;

import com.github.pagehelper.PageInfo;
import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.entity.task.Task;

import java.util.List;

/**
 * 任务
 */
public interface TaskService {
    /**
     * 按条件查询查询
     * @param task
     * @return
     */
    List<Task> getTaskList(Task task);

    /**
     * 按条件分页查询
     * @param task
     * @return
     */
    PageInfo<Task> getTaskListPage(Task task);

    /**
     * 保存数据
     * @param task
     * @return
     */
    ServerResponse saveTask(Task task);

    /**
     * 按id和用户id查询
     * @param task
     * @return
     */
    Task getTaskByIdAndUserId(Task task);

    /**
     * 按id和用户删除
     * @param ids
     * @param userId
     * @return
     */
    ServerResponse deleteTaskByIdsAndUserId(List<Long> ids, Long userId);

    /**
     * 按id和用户id修改任务状态
     * @param ids
     * @param userId
     * @return
     */
    ServerResponse updateStatusByIdsAndUserId(List<Long> ids, Long userId);
}
