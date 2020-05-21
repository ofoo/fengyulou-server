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

    /**
     * 获取当前登录用户id
     *
     * @return
     */
    public Long getUserId(String userKey) {
        try {
            User user = getUser(userKey);
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
    public User getUser(String userKey) {
        try {
            String userJsonStr = redisManager.get(userKey);
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
    public boolean verifyLogin(String userKey) {
        try {
            User user = getUser(userKey);
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
    public void logout(String userKey) {
        redisManager.delete(userKey);
    }

    /**
     * 用户登录
     *
     * @param user
     */
    public String login(User user) {
        String userKey = StringUtils.userKey(user.getId());
        redisManager.setDays(userKey, user, StringUtils.strTurnLong(TimeConstant.NUMBER_ONE));
        return userKey;
    }
}
