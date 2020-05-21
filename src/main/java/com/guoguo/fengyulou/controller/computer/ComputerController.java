package com.guoguo.fengyulou.controller.computer;

import com.guoguo.common.CurrentUserManager;
import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.entity.computer.Computer;
import com.guoguo.fengyulou.entity.member.Member;
import com.guoguo.fengyulou.entity.project.Project;
import com.guoguo.fengyulou.service.computer.ComputerService;
import com.guoguo.fengyulou.service.member.MemberService;
import com.guoguo.fengyulou.service.project.ProjectService;
import com.guoguo.util.ObjectUtils;
import com.guoguo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 与服务器管理
 */
@Controller
@RequestMapping("/fyl")
public class ComputerController {

    @Autowired
    private ComputerService computerService;
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
    @RequestMapping("/computer/list/page")
    public String list(HttpServletRequest request, HttpSession session, Computer computer) {
        computer.setUserId(currentUserManager.getUserId());
        request.setAttribute("pageInfo", computerService.getComputerListPage(computer));
        request.setAttribute("data", computer);
        return "computer/computer-list";
    }

    /**
     * 添加页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/computer/insert")
    public String insert(HttpServletRequest request, HttpSession session) {
        request.setAttribute("pageTitle", "添加任务");
        //获取用户id
        Long userId = currentUserManager.getUserId();
        // 查询项目列表
        Project project = new Project();
        project.setUserId(userId);
        request.setAttribute("projectList", projectService.getProjectList(project));
        // 查询人员列表
        Member member = new Member();
        member.setUserId(userId);
        request.setAttribute("memberList", memberService.getMemberList(member));
        return "computer/computer-save";
    }

    /**
     * 修改页面
     *
     * @param request
     * @param session
     * @param computer
     * @return
     */
    @RequestMapping("/computer/update")
    public String update(HttpServletRequest request, HttpSession session, Computer computer) {
        request.setAttribute("pageTitle", "修改任务");
        //获取用户id
        Long userId = currentUserManager.getUserId();
        // 查询任务
        computer.setUserId(userId);
        request.setAttribute("data", computerService.getComputerByIdAndUserId(computer));
        // 查询项目列表
        Project project = new Project();
        project.setUserId(userId);
        request.setAttribute("projectList", projectService.getProjectList(project));
        // 查询人员列表
        Member member = new Member();
        member.setUserId(userId);
        request.setAttribute("memberList", memberService.getMemberList(member));
        return "computer/computer-save";
    }

    /**
     * 保存数据
     *
     * @param computer
     * @return
     */
    @RequestMapping("/computer/ajax/save")
    @ResponseBody
    private ServerResponse ajaxSave(HttpSession session, Computer computer) {
        if (ObjectUtils.isNull(computer.getProjectId())) {
            return ServerResponse.createByErrorMessage("请选择项目名称");
        }
        if (StringUtils.isBlank(computer.getHost())) {
            return ServerResponse.createByErrorMessage("请输入主机");
        }
        if (StringUtils.isBlank(computer.getPort())) {
            return ServerResponse.createByErrorMessage("请输入端口");
        }
        if (StringUtils.isBlank(computer.getAccount())) {
            return ServerResponse.createByErrorMessage("请输入账号");
        }
        if (StringUtils.isBlank(computer.getPassword())) {
            return ServerResponse.createByErrorMessage("请输入密码");
        }
        computer.setUserId(currentUserManager.getUserId());
        return computerService.saveComputer(computer);
    }

    /**
     * 根据id删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/computer/ajax/delete")
    @ResponseBody
    private ServerResponse ajaxDelete(@RequestParam List<Long> ids, HttpSession session) {
        if (ObjectUtils.isNull(ids)) {
            return ServerResponse.createByErrorMessage("请选择数据");
        }
        return computerService.deleteComputerByIdsAndUserId(ids, currentUserManager.getUserId());
    }
}
