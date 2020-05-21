package com.guoguo.fengyulou.controller.task;

import com.guoguo.common.CurrentUserManager;
import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.entity.member.Member;
import com.guoguo.fengyulou.entity.project.Project;
import com.guoguo.fengyulou.entity.task.Task;
import com.guoguo.fengyulou.entity.task.label.TaskLabel;
import com.guoguo.fengyulou.service.member.MemberService;
import com.guoguo.fengyulou.service.project.ProjectService;
import com.guoguo.fengyulou.service.task.TaskService;
import com.guoguo.fengyulou.service.task.label.TaskLabelService;
import com.guoguo.util.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 任务管理
 */
@Controller
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
    @RequestMapping("/task/list/page")
    public String list(HttpServletRequest request, HttpSession session, Task task) {
        task.setUserId(currentUserManager.getUserId());
        request.setAttribute("pageInfo", taskService.getTaskListPage(task));
        request.setAttribute("data", task);
        return "task/task-list";
    }

    /**
     * 添加页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/task/insert")
    public String insert(HttpServletRequest request, HttpSession session) {
        request.setAttribute("pageTitle", "添加任务");
        //获取用户id
        Long userId = currentUserManager.getUserId();
        // 查询项目列表
        Project project = new Project();
        project.setUserId(userId);
        request.setAttribute("projectList", projectService.getProjectList(project));
        // 查询任务标签列表
        TaskLabel taskLabel = new TaskLabel();
        taskLabel.setUserId(userId);
        request.setAttribute("taskLabelList", taskLabelService.getTaskLabelList(taskLabel));
        // 查询人员列表
        Member member = new Member();
        member.setUserId(userId);
        request.setAttribute("memberList", memberService.getMemberList(member));
        return "task/task-save";
    }

    /**
     * 修改页面
     *
     * @param request
     * @param session
     * @param task
     * @return
     */
    @RequestMapping("/task/update")
    public String update(HttpServletRequest request, HttpSession session, Task task) {
        request.setAttribute("pageTitle", "修改任务");
        //获取用户id
        Long userId = currentUserManager.getUserId();
        // 查询任务
        task.setUserId(userId);
        request.setAttribute("data", taskService.getTaskByIdAndUserId(task));
        // 查询项目列表
        Project project = new Project();
        project.setUserId(userId);
        request.setAttribute("projectList", projectService.getProjectList(project));
        // 查询任务标签列表
        TaskLabel taskLabel = new TaskLabel();
        taskLabel.setUserId(userId);
        request.setAttribute("taskLabelList", taskLabelService.getTaskLabelList(taskLabel));
        // 查询人员列表
        Member member = new Member();
        member.setUserId(userId);
        request.setAttribute("memberList", memberService.getMemberList(member));
        return "task/task-save";
    }

    /**
     * 保存数据
     *
     * @param task
     * @return
     */
    @RequestMapping("/task/ajax/save")
    @ResponseBody
    private ServerResponse ajaxSave(HttpSession session, Task task) {
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
        task.setUserId(currentUserManager.getUserId());
        return taskService.saveTask(task);
    }

    /**
     * 根据id删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/task/ajax/delete")
    @ResponseBody
    private ServerResponse ajaxDelete(@RequestParam List<Long> ids, HttpSession session) {
        if (ObjectUtils.isNull(ids)) {
            return ServerResponse.createByErrorMessage("请选择数据");
        }
        return taskService.deleteTaskByIdsAndUserId(ids, currentUserManager.getUserId());
    }

    /**
     * 修改任务状态完成
     */
    @RequestMapping("/task/ajax/updateStatus")
    @ResponseBody
    private ServerResponse ajaxUpdateStatus(@RequestParam List<Long> ids, HttpSession session) {
        return taskService.updateStatusByIdsAndUserId(ids, currentUserManager.getUserId());
    }
}
