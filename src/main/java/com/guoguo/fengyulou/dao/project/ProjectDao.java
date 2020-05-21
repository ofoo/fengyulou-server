package com.guoguo.fengyulou.dao.project;

import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.entity.project.Project;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectDao {
    List<Project> getProjectList(Project project);

    int insertProject(Project project);

    int getProjectCountByProject(Project project);

    Project getProjectByIdAndUserId(Project project);

    int updateProjectByIdAndUserId(Project project);

    int getProjectCountByNameAndUserId(Project project);

    int deleteProjectByIdsAndUserId(@Param("ids") List<Long> ids, @Param("userId") Long userId);
}
