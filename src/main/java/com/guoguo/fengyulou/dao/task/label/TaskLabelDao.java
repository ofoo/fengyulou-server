package com.guoguo.fengyulou.dao.task.label;

import com.guoguo.fengyulou.entity.task.label.TaskLabel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskLabelDao {
    List<TaskLabel> getTaskLabelList(TaskLabel taskLabel);

    int insertTaskLabel(TaskLabel taskLabel);

    int getTaskLabelCountByTaskLabel(TaskLabel taskLabel);

    TaskLabel getTaskLabelByIdAndUserId(TaskLabel taskLabel);

    int updateTaskLabelByIdAndUserId(TaskLabel taskLabel);

    int getTaskLabelCountByNameAndUserId(TaskLabel taskLabel);

    int deleteTaskLabelByIdsAndUserId(@Param("ids") List<Long> ids, @Param("userId") Long userId);
}
