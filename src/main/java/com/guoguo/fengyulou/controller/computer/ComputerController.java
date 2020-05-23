package com.guoguo.fengyulou.controller.computer;

import com.github.pagehelper.PageInfo;
import com.guoguo.common.CurrentUserManager;
import com.guoguo.common.DataJson;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 与服务器管理
 */
@RestController
@RequestMapping("/fyl")
public class ComputerController {

    @Autowired
    private ComputerService computerService;
    @Autowired
    private CurrentUserManager currentUserManager;

    /**
     * 列表页面
     *
     * @return
     */
    @RequestMapping("/computer/list")
    public DataJson list(@RequestParam String userKey, Computer computer) {
        computer.setUserId(currentUserManager.getUserId(userKey));
        PageInfo<Computer> pageInfo = computerService.getComputerListPage(computer);
        return DataJson.list(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 保存数据
     *
     * @param computer
     * @return
     */
    @RequestMapping("/computer/save")
    private ServerResponse save(@RequestParam String userKey, Computer computer) {
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
        computer.setUserId(currentUserManager.getUserId(userKey));
        return computerService.saveComputer(computer);
    }

    /**
     * 根据id删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/computer/delete")
    @ResponseBody
    private ServerResponse delete(@RequestParam List<Long> ids, @RequestParam String userKey) {
        if (ObjectUtils.isNull(ids)) {
            return ServerResponse.createByErrorMessage("请选择数据");
        }
        return computerService.deleteComputerByIdsAndUserId(ids, currentUserManager.getUserId(userKey));
    }
}
