package com.fu.controller;

import com.fu.bean.Admin;
import com.fu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 管理员控制器
 * @Author Administrator
 * @Date 2020/1/7 16:08
 */
@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/getAdminById")
    @ResponseBody
    public String getAdminById(Integer id) {
        Admin admin = adminService.getAdminById(id);
        System.out.println(admin);
        return "success";
    }
}
