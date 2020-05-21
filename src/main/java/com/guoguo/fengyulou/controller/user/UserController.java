package com.guoguo.fengyulou.controller.user;

import com.guoguo.common.CommonConstant;
import com.guoguo.common.CurrentUserManager;
import com.guoguo.common.RedisManager;
import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.entity.user.User;
import com.guoguo.fengyulou.service.user.UserService;
import com.guoguo.util.ObjectUtils;
import com.guoguo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户管理
 */
@RestController
@RequestMapping("/fyl")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisManager redisManager;
    @Autowired
    private CurrentUserManager currentUserManager;

    /**
     * 列表页面
     *
     * @param request
     * @param user
     * @return
     */
    @RequestMapping("/user/list/page")
    public String list(HttpServletRequest request, HttpSession session, User user, Model model) {
        request.setAttribute("data", user);
        request.setAttribute("pageInfo", userService.getUserListPage(user));
        return "user/user-list";
    }

    /**
     * 添加页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/user/insert")
    public String insert(HttpServletRequest request, HttpSession session) {
        request.setAttribute("pageTitle", "添加用户");
        request.setAttribute("password", "111111");
        return "user/user-save";
    }

    /**
     * 修改页面
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/user/update/{id}")
    public String update(HttpServletRequest request, HttpSession session, @PathVariable Long id) {
        request.setAttribute("pageTitle", "修改用户");
        request.setAttribute("data", userService.getUserById(id));
        return "user/user-save";
    }

    @RequestMapping("/user/ajax/save")
    @ResponseBody
    public ServerResponse ajaxSave(HttpSession session, User user) {
        if (StringUtils.isBlank(user.getLoginName())) {
            return ServerResponse.createByErrorMessage("请输入账号");
        }
        if (StringUtils.isBlank(user.getName())) {
            return ServerResponse.createByErrorMessage("请输入姓名");
        }
        return userService.saveUser(user);
    }

    /**
     * 删除数据
     *
     * @param ids
     * @return
     */
    @RequestMapping("/user/ajax/delete")
    @ResponseBody
    public ServerResponse ajaxDelete(@RequestParam List<Long> ids, HttpSession session) {
        if (ObjectUtils.isNull(ids)) {
            return ServerResponse.createByErrorMessage("请选择数据");
        }
        return userService.deleteUserByIds(ids);
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @RequestMapping("/user/login")
    public ServerResponse login(HttpServletResponse response, User user) {
        User tem = userService.login(user);
        if (ObjectUtils.isNotNull(tem)) {
            String userKey = currentUserManager.login(tem);
            return ServerResponse.createBySuccess("登录成功", userKey);
        }
        return ServerResponse.createByErrorMessage("账号或密码错误");
    }

    /**
     * 用户退出
     *
     * @param userKey
     * @return
     */
    @RequestMapping("/user/logout")
    public String logout(@RequestParam String userKey) {
        currentUserManager.logout(userKey);
        return "redirect:" + CommonConstant.LOGIN;
    }

    /**
     * 修改用户密码
     *
     * @param response
     * @param pwd
     * @return
     */
    @RequestMapping("/user/ajaxUpdatePwd")
    @ResponseBody
    public ServerResponse ajaxUpdatePwd(@RequestParam String userKey, @RequestParam String pwd) {
        ServerResponse serverResponse = userService.updatePasswordById(userKey, pwd);
        if (serverResponse.isSuccess()) {
            currentUserManager.logout(userKey);
        }
        return serverResponse;
    }
}
