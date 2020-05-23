package com.guoguo.fengyulou.controller.user;

import com.github.pagehelper.PageInfo;
import com.guoguo.common.*;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @RequestMapping("/user/list")
    public DataJson list(User user) {
        PageInfo<User> pageInfo = userService.getUserListPage(user);
        return DataJson.list(pageInfo.getTotal(), pageInfo.getList());
    }

    @RequestMapping("/user/save")
    public ServerResponse save(HttpSession session, User user) {
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
    @RequestMapping("/user/delete")
    public ServerResponse delete(@RequestParam List<Long> ids, HttpSession session) {
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
            Map<String, String> map = new HashMap<>();
            map.put("userKey", userKey);
            map.put("currUserName", tem.getName());
            return ServerResponse.createBySuccess("登录成功", map);
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
    public ServerResponse logout(@RequestParam String userKey) {
        currentUserManager.logout(userKey);
        return ServerResponse.createBySuccess();
    }

    /**
     * 修改用户密码
     *
     * @param userKey
     * @param oldPwd
     * @param newPwd
     * @return
     */
    @RequestMapping("/user/update/pwd")
    public ServerResponse updatePwd(@RequestParam String userKey, @RequestParam String oldPwd, @RequestParam String newPwd, @RequestParam String newPwdTwo) {
        return userService.updatePasswordById(userKey, oldPwd, newPwd, newPwdTwo);
    }

    /**
     * 获取用户姓名
     *
     * @param userKey
     * @return
     */
    @RequestMapping("/user/name")
    public ServerResponse userName(@RequestParam String userKey) {
        return ServerResponse.createBySuccess(currentUserManager.getUserName(userKey));
    }
}
