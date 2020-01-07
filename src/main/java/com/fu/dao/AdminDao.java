package com.fu.dao;

import com.fu.bean.Admin;

/**
 * 管理员数据操作层
 * @Author Administrator
 * @Date 2020/1/7 16:12
 */
public interface AdminDao {

    /**
     *  根据id查询管理员
     * @param id    管理员id
     * @return 返回查询到的管理员
     */
    Admin getAdminById(Integer id);

    /**
     * 根据账号查询admin，用于登录
     * @param username 账号
     * @return 返回查到的数据
     */
    Admin getAdminByUsername(String username);
}
