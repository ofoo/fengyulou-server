package com.guoguo.fengyulou.dao.user;

import com.guoguo.fengyulou.entity.user.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    User getUserByLoginName(@Param("loginName") String loginName);

    int updatePasswordById(User user);

    List<User> getUserList(User user);

    User getUserById(Long id);

    int updateUserById(User user);

    int insertUser(User user);

    int getUserCountByProject(User user);

    int getUserCountByLoginName(String loginName);

    int deleteUserByIds(List<Long> ids);
}
