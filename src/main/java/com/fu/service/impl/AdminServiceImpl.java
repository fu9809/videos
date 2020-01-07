package com.fu.service.impl;

import com.fu.bean.Admin;
import com.fu.dao.AdminDao;
import com.fu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  管理员业务实现类
 * @Author Administrator
 * @Date 2020/1/7 16:11
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin getAdminById(Integer id) {
        return adminDao.getAdminById(id);
    }
}
