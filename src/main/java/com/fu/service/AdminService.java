package com.fu.service;

import com.fu.bean.Admin;

/**
 *  管理员事务层
 * @Author Administrator
 * @Date 2020/1/7 16:10
 */
public interface AdminService {
    /**
     *  根据id查询管理员
     * @param id    管理员id
     * @return 返回查询到的管理员
     */
    Admin getAdminById(Integer id);
}
