package com.guoguo.fengyulou.controller.user;

import com.guoguo.common.CommonConstant;
import com.guoguo.common.CurrentUserManager;
import com.guoguo.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private CurrentUserManager currentUserManager;

    /**
     * 登录页面
     *
     * @return
     */
    @RequestMapping("/")
    public String login(HttpSession session) {
        if (currentUserManager.verifyLogin()) {
            return "redirect:" + CommonConstant.INDEX;
        }
        return "login";
    }
}
