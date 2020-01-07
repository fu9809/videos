package com.fu.dao;

import com.fu.bean.User;

/**
 * @Author Administrator
 * @Date 2020/1/7 16:05
 */
public interface UserDao {

    User selectById(Integer id);
}
