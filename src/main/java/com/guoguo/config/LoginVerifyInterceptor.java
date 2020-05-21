package com.guoguo.config;

import com.guoguo.common.CommonConstant;
import com.guoguo.common.CurrentUserManager;
import com.guoguo.fengyulou.constant.UserConstant;
import com.guoguo.fengyulou.entity.user.User;
import com.guoguo.util.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录验证
 */
@Component
@Slf4j
public class LoginVerifyInterceptor implements HandlerInterceptor {

    @Autowired
    private CurrentUserManager currentUserManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userKey = request.getParameter("userKey");
        log.info("userKey: " + userKey);
        User user = currentUserManager.getUser(userKey);
        if (ObjectUtils.isNull(user)) {
            response.sendRedirect(CommonConstant.LOGIN);
            return false;
        }
        request.setAttribute(CommonConstant.CUR_USER, user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
