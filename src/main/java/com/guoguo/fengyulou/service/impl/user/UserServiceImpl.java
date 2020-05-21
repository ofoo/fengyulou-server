package com.guoguo.fengyulou.service.impl.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guoguo.common.CommonConstant;
import com.guoguo.common.CurrentUserManager;
import com.guoguo.common.ResponseCode;
import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.dao.user.UserDao;
import com.guoguo.fengyulou.entity.user.User;
import com.guoguo.fengyulou.service.user.UserService;
import com.guoguo.util.MD5Util;
import com.guoguo.util.ObjectUtils;
import com.guoguo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private CurrentUserManager currentUserManager;

    @Override
    public ServerResponse login(User user) {
        User tem = userDao.getUserByLoginName(user.getLoginName().trim());
        if (ObjectUtils.isNotNull(tem)) {
            user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
            if (tem.getPassword().equals(user.getPassword())) {
                return ServerResponse.createBySuccess("登录成功", tem);
            }
        }
        return ServerResponse.createByErrorMessage("账号或密码错误");
    }

    @Override
    public ServerResponse updatePasswordById(String password) {
        User user = currentUserManager.getUser();
        user.setPassword(MD5Util.MD5EncodeUtf8(password));
        int rows = userDao.updatePasswordById(user);
        if (rows > 0) {
            return ServerResponse.createBySuccessMessage("修改成功");
        }
        return ServerResponse.createByErrorMessage("修改失败");
    }

    @Override
    public PageInfo<User> getUserListPage(User user) {
        PageHelper.startPage(user.getPageNum() == null ? 1 : user.getPageNum(), user.getPageSize() == null ? 10 : user.getPageSize());
        List<User> list = userDao.getUserList(user);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public ServerResponse saveUser(User user) {
        user.setLoginName(user.getLoginName().trim());
        user.setName(user.getName().trim());
        if (ObjectUtils.isNotNull(user.getId())) {
            // 验证数据是否重复
            int count = userDao.getUserCountByProject(user);
            if (count > 0) {
                return ServerResponse.createByError(ResponseCode.EXIST);
            }
            // 修改
            if (StringUtils.isNotBlank(user.getPassword())) {
                user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
            }
            int rows = userDao.updateUserById(user);
            if (rows > 0) {
                return ServerResponse.createBySuccess();
            }
        } else {
            // 验证数据是否重复
            int count = userDao.getUserCountByLoginName(user.getLoginName());
            if (count > 0) {
                return ServerResponse.createByError(ResponseCode.EXIST);
            }
            // 添加
            if (StringUtils.isBlank(user.getPassword())) {
                user.setPassword(MD5Util.MD5EncodeUtf8(CommonConstant.DEF_PWD));
            } else {
                user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
            }
            user.setType(0);
            int rows = userDao.insertUser(user);
            if (rows > 0) {
                return ServerResponse.createBySuccess(user.getId());
            }
        }
        return ServerResponse.createByError();
    }

    @Override
    public ServerResponse deleteUserByIds(List<Long> ids) {
        int rows = userDao.deleteUserByIds(ids);
        if (rows > 0) {
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }
}
