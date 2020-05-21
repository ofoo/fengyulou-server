package com.guoguo.fengyulou.service.user;

import com.github.pagehelper.PageInfo;
import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.entity.user.User;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.List;

public interface UserService {
    /**
     * 用户登录验证
     *
     * @param user
     * @return
     */
    ServerResponse login(User user);

    /**
     * 按id修改用户密码
     *
     * @param password
     * @return
     */
    ServerResponse updatePasswordById(String password);

    /**
     * 按条件分页查询
     *
     * @param user
     * @return
     */
    PageInfo<User> getUserListPage(User user);

    /**
     * 按id查询
     *
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    ServerResponse saveUser(User user);

    /**
     * 按id删除数据
     *
     * @param ids
     * @return
     */
    ServerResponse deleteUserByIds(List<Long> ids);
}
