package com.guoguo.fengyulou.service.impl.task;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.dao.task.TaskDao;
import com.guoguo.fengyulou.entity.task.Task;
import com.guoguo.fengyulou.service.task.TaskService;
import com.guoguo.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;

    @Override
    public List<Task> getTaskList(Task task) {
        return taskDao.getTaskList(task);
    }

    @Override
    public PageInfo<Task> getTaskListPage(Task task) {
        PageHelper.startPage(task.getPageNum() == null ? 1 : task.getPageNum(), task.getPageSize() == null ? 10 : task.getPageSize());
        List<Task> list = taskDao.getTaskList(task);
        PageInfo<Task> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public ServerResponse saveTask(Task task) {
        // 去除空格
        task.setSketch(task.getSketch().trim());
        if (ObjectUtils.isNotNull(task.getId())) {
            // 按id修改数据
            int rows = taskDao.updateTaskByIdAndUserId(task);
            if (rows > 0) {
                return ServerResponse.createBySuccess();
            }
        } else {
            // 添加数据
            int rows = taskDao.insertTask(task);
            if (rows > 0) {
                return ServerResponse.createBySuccess(task.getId());
            }
        }
        return ServerResponse.createByError();
    }

    @Override
    public Task getTaskByIdAndUserId(Task task) {
        if (ObjectUtils.isNull(task.getId())) {
            return null;
        }
        return taskDao.getTaskByIdAndUserId(task);
    }

    @Override
    public ServerResponse deleteTaskByIdsAndUserId(List<Long> ids, Long userId) {
        int rows = taskDao.deleteTaskByIdsAndUserId(ids, userId);
        if (rows > 0) {
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }

    @Override
    public ServerResponse updateStatusByIdsAndUserId(List<Long> ids, Long userId) {
        int rows = taskDao.updateStatusByIdsAndUserId(ids, userId);
        if (rows > 0) {
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }
}
