package com.fu.service;

import com.fu.bean.User;

/**
 * 用户业务层
 * @Author Administrator
 * @Date 2020/1/7 15:05
 */
public interface UserService {
    /**
     * 添加用户
     * @param user 用户信息
     * @return 返回操作成功的个数
     */
    int addUser(User user);
}
