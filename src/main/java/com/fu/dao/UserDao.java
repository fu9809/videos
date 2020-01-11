package com.fu.dao;

import com.fu.bean.User;

/**
 * @Author Administrator
 * @Date 2020/1/7 16:05
 */
public interface UserDao {

    User selectById(Integer id);

    /**
     * 添加用户
     * @param user 用户信息
     * @return 返回操作成功的个数
     */
    int addUser(User user);
}
