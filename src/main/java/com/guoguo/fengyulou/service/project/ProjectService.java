package com.guoguo.fengyulou.service.project;

import com.github.pagehelper.PageInfo;
import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.entity.project.Project;

import java.util.List;

public interface ProjectService {
    /**
     * 按条件查询
     *
     * @param project
     * @return
     */
    List<Project> getProjectList(Project project);

    /**
     * 按条件分页查询
     *
     * @param project
     * @return
     */
    PageInfo<Project> getProjectListPage(Project project);

    /**
     * 保存项目
     * @param project
     * @return
     */
    ServerResponse saveProject(Project project);

    /**
     * 按id和用户id查询
     * @param project
     * @return
     */
    Project getProjectByIdAndUserId(Project project);

    /**
     * 按id和用户id删除
     * @param ids
     * @param userId
     * @return
     */
    ServerResponse deleteProjectByIdsAndUserId(List<Long> ids, Long userId);
}
