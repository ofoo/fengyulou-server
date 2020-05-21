package com.guoguo.fengyulou.controller.project;

import com.guoguo.common.CurrentUserManager;
import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.entity.project.Project;
import com.guoguo.fengyulou.service.project.ProjectService;
import com.guoguo.util.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 项目管理
 */
@Controller
@RequestMapping("/fyl")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private CurrentUserManager currentUserManager;

    /**
     * 列表页面
     *
     * @return
     */
    @RequestMapping("/project/list/page")
    public String list(HttpServletRequest request, @RequestParam String userKey, Project project) {
        project.setUserId(currentUserManager.getUserId(userKey));
        request.setAttribute("pageInfo", projectService.getProjectListPage(project));
        request.setAttribute("data", project);
        return "project/project-list";
    }

    /**
     * 添加页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/project/insert")
    public String insert(HttpServletRequest request, @RequestParam String userKey) {
        request.setAttribute("pageTitle", "添加项目");
        return "project/project-save";
    }

    /**
     * 修改页面
     *
     * @param request
     * @param session
     * @param project
     * @return
     */
    @RequestMapping("/project/update")
    public String update(HttpServletRequest request, @RequestParam String userKey, Project project) {
        request.setAttribute("pageTitle", "修改项目");
        // 查询项目
        project.setUserId(currentUserManager.getUserId(userKey));
        request.setAttribute("data", projectService.getProjectByIdAndUserId(project));
        return "project/project-save";
    }

    /**
     * 保存数据
     *
     * @param project
     * @return
     */
    @RequestMapping("/project/ajax/save")
    @ResponseBody
    public ServerResponse ajaxSave(Project project, @RequestParam String userKey) {
        if (StringUtils.isBlank(project.getName())) {
            return ServerResponse.createByErrorMessage("请输入项目名称");
        }
        project.setUserId(currentUserManager.getUserId(userKey));
        return projectService.saveProject(project);
    }

    /**
     * 删除数据
     *
     * @param ids
     * @return
     */
    @RequestMapping("/project/ajax/delete")
    @ResponseBody
    public ServerResponse ajaxDelete(@RequestParam List<Long> ids, @RequestParam String userKey) {
        if (ObjectUtils.isNull(ids)) {
            return ServerResponse.createByErrorMessage("请选择数据");
        }
        return projectService.deleteProjectByIdsAndUserId(ids, currentUserManager.getUserId(userKey));
    }

    /**
     * 下拉选列表
     *
     * @param request
     * @return
     */
    @RequestMapping("/project/ajax/list")
    public String ajaxList(HttpServletRequest request, @RequestParam String userKey) {
        Project project = new Project();
        project.setUserId(currentUserManager.getUserId(userKey));
        request.setAttribute("list", projectService.getProjectList(project));
        return "common/select-item";
    }
}
