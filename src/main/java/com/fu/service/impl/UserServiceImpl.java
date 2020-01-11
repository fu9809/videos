package com.fu.service.impl;

import com.fu.bean.User;
import com.fu.dao.UserDao;
import com.fu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户业务层实现类
 * @Author Administrator
 * @Date 2020/1/7 16:05
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }
}
