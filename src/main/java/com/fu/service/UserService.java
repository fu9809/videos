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

    /**
     * 根据邮箱查询用户
     * @param email 邮箱
     * @return 返回查询到的用户
     */
    User getUserByEmail(String email);

    /**
     * 根据手机号查询用户
     * @param phone 手机号
     * @return 返回查询到的用户
     */
    User getUserByPhone(String phone);

    /**
     * 根据账号查询用户
     * @param code 账号
     * @return 返回查询到的用户
     */
    User getUserByCode(String code);
}
