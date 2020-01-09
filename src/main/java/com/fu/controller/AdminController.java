package com.fu.controller;

import com.fu.bean.Admin;
import com.fu.comment.Msg;
import com.fu.service.AdminService;
import com.fu.utils.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 管理员控制器
 * @Author Administrator
 * @Date 2020/1/7 16:08
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     *  仅做测试使用
     * @param id postman 传入id
     * @return  返回一个字符串
     */
    @RequestMapping("/getAdminById")
    @ResponseBody
    public String getAdminById(Integer id) {
        Admin admin = adminService.getAdminById(id);
        System.out.println(admin);
        return "success";
    }

    @GetMapping("/verifyLogin.do")
    @ResponseBody
    public Msg verifyLogin(HttpSession session) {
        Admin admin = (Admin) session.getAttribute(StrUtils.LOGIN_ADMIN);
        if (admin != null) {
            return new Msg();
        }
        return new Msg(1, "还未登录");
    }

    @RequestMapping("/login.do")
    @ResponseBody
    public Msg login(String username, String password, String code, HttpSession session) {
        // 验证码

        Admin admin = adminService.login(username, password);
        session.setAttribute(StrUtils.LOGIN_ADMIN, admin);
        return new Msg(0, "登录成功");
    }
}
