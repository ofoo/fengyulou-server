package com.guoguo.fengyulou.controller.project;

import com.github.pagehelper.PageInfo;
import com.guoguo.common.CurrentUserManager;
import com.guoguo.common.DataJson;
import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.entity.project.Project;
import com.guoguo.fengyulou.service.project.ProjectService;
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
 * 项目管理
 */
@RestController
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
    @RequestMapping("/project/list")
    public DataJson list(@RequestParam String userKey, Project project) {
        project.setUserId(currentUserManager.getUserId(userKey));
        PageInfo<Project> pageInfo = projectService.getProjectListPage(project);
        return DataJson.list(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 保存数据
     *
     * @param project
     * @return
     */
    @RequestMapping("/project/save")
    public ServerResponse save(Project project, @RequestParam String userKey) {
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
    @RequestMapping("/project/delete")
    public ServerResponse delete(@RequestParam List<Long> ids, @RequestParam String userKey) {
        if (ObjectUtils.isNull(ids)) {
            return ServerResponse.createByErrorMessage("请选择数据");
        }
        return projectService.deleteProjectByIdsAndUserId(ids, currentUserManager.getUserId(userKey));
    }

    /**
     * 下拉选列表
     *
     * @param userKey
     * @return
     */
    @RequestMapping("/project/list/content")
    public ServerResponse listContent(@RequestParam String userKey) {
        Project project = new Project();
        project.setUserId(currentUserManager.getUserId(userKey));
        return ServerResponse.createBySuccess(projectService.getProjectList(project));
    }
}
