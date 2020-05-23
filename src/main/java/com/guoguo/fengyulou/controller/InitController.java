package com.guoguo.fengyulou.controller;

import com.guoguo.common.CurrentUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/fyl")
public class InitController {

    @Autowired
    private CurrentUserManager currentUserManager;

    /**
     * 获取初始化数据
     *
     * @param userKey
     * @return
     */
    @RequestMapping("/init")
    public String init(@RequestParam String userKey) {
        Integer userType = currentUserManager.getUserType(userKey);
        if (userType == 1) {
            return "{\"homeInfo\":{\"title\":\"首页\",\"href\":\"page/task/task-list.html?t=1\"},\"logoInfo\":{\"title\":\"风雨楼\",\"image\":\"images/logo.png\",\"href\":\"\"},\"menuInfo\":[{\"title\":\"常规管理\",\"icon\":\"fa fa-address-book\",\"href\":\"\",\"target\":\"_self\",\"child\":[{\"title\":\"任务管理\",\"href\":\"\",\"icon\":\"fa fa-home\",\"target\":\"_self\",\"child\":[{\"title\":\"任务列表\",\"href\":\"page/task/task-list.html\",\"icon\":\"fa fa-tachometer\",\"target\":\"_self\"},{\"title\":\"任务标签\",\"href\":\"page/task/label/task-label-list.html\",\"icon\":\"fa fa-tachometer\",\"target\":\"_self\"}]},{\"title\":\"云服务器\",\"href\":\"\",\"icon\":\"fa fa-home\",\"target\":\"_self\",\"child\":[{\"title\":\"机器列表\",\"href\":\"page/computer/computer-list.html\",\"icon\":\"fa fa-tachometer\",\"target\":\"_self\"}]},{\"title\":\"人员管理\",\"href\":\"\",\"icon\":\"fa fa-home\",\"target\":\"_self\",\"child\":[{\"title\":\"人员列表\",\"href\":\"page/member/member-list.html\",\"icon\":\"fa fa-tachometer\",\"target\":\"_self\"},{\"title\":\"人员标签\",\"href\":\"page/member/label/task-label-list.html\",\"icon\":\"fa fa-tachometer\",\"target\":\"_self\"}]},{\"title\":\"项目管理\",\"href\":\"\",\"icon\":\"fa fa-home\",\"target\":\"_self\",\"child\":[{\"title\":\"项目列表\",\"href\":\"page/project/project-list.html\",\"icon\":\"fa fa-tachometer\",\"target\":\"_self\"}]},{\"title\":\"用户管理\",\"href\":\"\",\"icon\":\"fa fa-home\",\"target\":\"_self\",\"child\":[{\"title\":\"用户列表\",\"href\":\"page/user/user-list.html\",\"icon\":\"fa fa-tachometer\",\"target\":\"_self\"}]}]}]}";
        }
        return "{\"homeInfo\":{\"title\":\"首页\",\"href\":\"page/task/task-list.html?t=1\"},\"logoInfo\":{\"title\":\"风雨楼\",\"image\":\"images/logo.png\",\"href\":\"\"},\"menuInfo\":[{\"title\":\"常规管理\",\"icon\":\"fa fa-address-book\",\"href\":\"\",\"target\":\"_self\",\"child\":[{\"title\":\"任务管理\",\"href\":\"\",\"icon\":\"fa fa-home\",\"target\":\"_self\",\"child\":[{\"title\":\"任务列表\",\"href\":\"page/task/task-list.html\",\"icon\":\"fa fa-tachometer\",\"target\":\"_self\"},{\"title\":\"任务标签\",\"href\":\"page/task/label/task-label-list.html\",\"icon\":\"fa fa-tachometer\",\"target\":\"_self\"}]},{\"title\":\"云服务器\",\"href\":\"\",\"icon\":\"fa fa-home\",\"target\":\"_self\",\"child\":[{\"title\":\"机器列表\",\"href\":\"page/computer/computer-list.html\",\"icon\":\"fa fa-tachometer\",\"target\":\"_self\"}]},{\"title\":\"人员管理\",\"href\":\"\",\"icon\":\"fa fa-home\",\"target\":\"_self\",\"child\":[{\"title\":\"人员列表\",\"href\":\"page/member/member-list.html\",\"icon\":\"fa fa-tachometer\",\"target\":\"_self\"},{\"title\":\"人员标签\",\"href\":\"page/member/label/task-label-list.html\",\"icon\":\"fa fa-tachometer\",\"target\":\"_self\"}]},{\"title\":\"项目管理\",\"href\":\"\",\"icon\":\"fa fa-home\",\"target\":\"_self\",\"child\":[{\"title\":\"项目列表\",\"href\":\"page/project/project-list.html\",\"icon\":\"fa fa-tachometer\",\"target\":\"_self\"}]}]}]}";
    }
}
