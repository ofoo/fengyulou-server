package com.guoguo.common;

import com.guoguo.fengyulou.entity.user.User;
import com.guoguo.util.ObjectUtils;
import com.guoguo.util.StringUtils;
import com.guoguo.util.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 当前用户管理
 */
@Component
@Slf4j
public class CurrentUserManager {
    @Autowired
    private RedisManager redisManager;
    @Autowired
    private HttpServletRequest request;

    /**
     * 获取当前登录用户id
     *
     * @return
     */
    public Long getUserId() {
        try {
            User user = getUser();
            if (ObjectUtils.isNotNull(user)) {
                return user.getId();
            }
        } catch (Exception e) {
            log.error("获取用户id异常", e);
        }
        return 0L;
    }

    /**
     * 获取当前用户
     *
     * @return
     */
    public User getUser() {
        try {
            String userJsonStr = redisManager.get(WebUtils.getCookie(request, CommonConstant.UUID));
            if (StringUtils.isNotBlank(userJsonStr)) {
                User user = StaticObject.gson.fromJson(userJsonStr, User.class);
                return user;
            }
        } catch (Exception e) {
            log.error("获取用户id异常", e);
        }
        return null;
    }

    /**
     * 验证用户是否登录
     *
     * @return
     */
    public boolean verifyLogin() {
        try {
            User user = getUser();
            if (ObjectUtils.isNotNull(user)) {
                return true;
            }
        } catch (Exception e) {
            log.error("验证用户是否登录异常", e);
        }
        return false;
    }

    /**
     * 用户退出
     */
    public void logout(HttpServletResponse response) {
        redisManager.delete(WebUtils.getCookie(request, CommonConstant.UUID));
        WebUtils.deleteCookie(request, response, CommonConstant.UUID);
    }

    /**
     * 用户登录
     *
     * @param response
     * @param user
     */
    public void login(HttpServletResponse response, User user) {
        String uuid = StringUtils.uuid();
        redisManager.setDays(uuid, user, StringUtils.strTurnLong(TimeConstant.NUMBER_ONE));
        WebUtils.setCookie(response, CommonConstant.UUID, uuid, StringUtils.strTurnInt(TimeConstant.NUMBER_ONE));
    }
}
