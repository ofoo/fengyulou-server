package com.guoguo.fengyulou.controller.task;

import com.github.pagehelper.PageInfo;
import com.guoguo.common.CurrentUserManager;
import com.guoguo.common.DataJson;
import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.entity.task.Task;
import com.guoguo.fengyulou.service.member.MemberService;
import com.guoguo.fengyulou.service.project.ProjectService;
import com.guoguo.fengyulou.service.task.TaskService;
import com.guoguo.fengyulou.service.task.label.TaskLabelService;
import com.guoguo.util.ObjectUtils;
import com.guoguo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 任务管理
 */
@RestController
@RequestMapping("/fyl")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskLabelService taskLabelService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private CurrentUserManager currentUserManager;

    /**
     * 列表页面
     *
     * @return
     */
    @RequestMapping("/task/list")
    public DataJson list(Task task, @RequestParam String userKey) {
        //存放用户id
        task.setUserId(currentUserManager.getUserId(userKey));
        //去除参数空格
        task.setProjectName(StringUtils.isNotBlank(task.getProjectName()) ? task.getProjectName().trim() : null);
        task.setMemberName(StringUtils.isNotBlank(task.getMemberName()) ? task.getMemberName().trim() : null);
        task.setTaskLabelName(StringUtils.isNotBlank(task.getTaskLabelName()) ? task.getTaskLabelName().trim() : null);
        //查询数据
        PageInfo<Task> pageInfo = taskService.getTaskListPage(task);
        return DataJson.list(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 保存数据
     *
     * @param task
     * @return
     */
    @RequestMapping("/task/save")
    public ServerResponse save(@RequestParam String userKey, Task task) {
        if (StringUtils.isBlank(task.getSketch())) {
            return ServerResponse.createByErrorMessage("请输入任务简述");
        }
        if (ObjectUtils.isNull(task.getProjectId())) {
            return ServerResponse.createByErrorMessage("请选择项目名称");
        }
        if (ObjectUtils.isNull(task.getTaskLabelId())) {
            return ServerResponse.createByErrorMessage("请选择任务标签");
        }
        if (ObjectUtils.isNull(task.getMemberId())) {
            return ServerResponse.createByErrorMessage("请选择执行人");
        }
        if (task.getStatus() == null || (task.getStatus() < 0 && task.getStatus() > 1)) {
            return ServerResponse.createByErrorMessage("请选择任务状态");
        }
        task.setUserId(currentUserManager.getUserId(userKey));
        return taskService.saveTask(task);
    }

    /**
     * 根据id删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/task/delete")
    public ServerResponse delete(@RequestParam List<Long> ids, @RequestParam String userKey) {
        if (ObjectUtils.isNull(ids)) {
            return ServerResponse.createByErrorMessage("请选择数据");
        }
        return taskService.deleteTaskByIdsAndUserId(ids, currentUserManager.getUserId(userKey));
    }

    /**
     * 修改任务状态完成
     */
    @RequestMapping("/task/ajax/updateStatus")
    @ResponseBody
    public ServerResponse ajaxUpdateStatus(@RequestParam List<Long> ids, @RequestParam String userKey) {
        return taskService.updateStatusByIdsAndUserId(ids, currentUserManager.getUserId(userKey));
    }
}
