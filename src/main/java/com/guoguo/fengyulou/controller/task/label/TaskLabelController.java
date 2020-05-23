package com.guoguo.fengyulou.controller.task.label;

import com.github.pagehelper.PageInfo;
import com.guoguo.common.CurrentUserManager;
import com.guoguo.common.DataJson;
import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.entity.task.label.TaskLabel;
import com.guoguo.fengyulou.service.task.label.TaskLabelService;
import com.guoguo.util.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 任务标签标签管理
 */
@RestController
@RequestMapping("/fyl")
public class TaskLabelController {

    @Autowired
    private TaskLabelService taskLabelService;
    @Autowired
    private CurrentUserManager currentUserManager;

    /**
     * 列表页面
     *
     * @param request
     * @param taskLabel
     * @return
     */
    @RequestMapping("/taskLabel/list")
    public DataJson list(@RequestParam String userKey, TaskLabel taskLabel) {
        taskLabel.setUserId(currentUserManager.getUserId(userKey));
        PageInfo<TaskLabel> pageInfo = taskLabelService.getTaskLabelListPage(taskLabel);
        return DataJson.list(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 保存数据
     *
     * @param taskLabel
     * @return
     */
    @RequestMapping("/taskLabel/save")
    public ServerResponse save(TaskLabel taskLabel, @RequestParam String userKey) {
        if (StringUtils.isBlank(taskLabel.getName())) {
            return ServerResponse.createByErrorMessage("请输入任务标签名称");
        }
        taskLabel.setUserId(currentUserManager.getUserId(userKey));
        return taskLabelService.saveTaskLabel(taskLabel);
    }

    /**
     * 删除数据
     *
     * @param ids
     * @return
     */
    @RequestMapping("/taskLabel/ajax/delete")
    @ResponseBody
    public ServerResponse ajaxDelete(@RequestParam List<Long> ids, @RequestParam String userKey) {
        if (ObjectUtils.isNull(ids)) {
            return ServerResponse.createByErrorMessage("请选择数据");
        }
        return taskLabelService.deleteTaskLabelByIdsAndUserId(ids, currentUserManager.getUserId(userKey));
    }

    /**
     * 下拉选子项
     *
     * @return
     */
    @RequestMapping("/taskLabel/list/content")
    public ServerResponse listContent(@RequestParam String userKey) {
        TaskLabel taskLabel = new TaskLabel();
        taskLabel.setUserId(currentUserManager.getUserId(userKey));
        return ServerResponse.createBySuccess(taskLabelService.getTaskLabelList(taskLabel));
    }
}

