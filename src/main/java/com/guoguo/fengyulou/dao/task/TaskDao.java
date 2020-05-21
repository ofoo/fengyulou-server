package com.guoguo.fengyulou.dao.task;

import com.guoguo.fengyulou.entity.task.Task;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskDao {
    List<Task> getTaskList(Task task);

    int insertTask(Task task);

    Task getTaskByIdAndUserId(Task task);

    int updateTaskByIdAndUserId(Task task);

    int deleteTaskByIdsAndUserId(@Param("ids") List<Long> ids, @Param("userId") Long userId);

    int updateStatusByIdsAndUserId(@Param("ids") List<Long> ids, @Param("userId") Long userId);
}
