package com.fu.controller;

import com.fu.bean.Admin;
import com.fu.comment.Msg;
import com.fu.service.AdminService;
import com.fu.utils.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping("/login.do")
    @ResponseBody
    public Msg login(String username, String password, String code, HttpSession session) {
        // 验证码
        String vCode = ((String) session.getAttribute(StrUtils.LOGIN_CODE)).toLowerCase();
        if (!vCode.equals(code)) {
            return new Msg(1, "验证码错误");
        }

        Admin adminSession = (Admin) session.getAttribute(StrUtils.LOGIN_ADMIN);
        if (adminSession != null) {
            return new Msg(1, "您已登录，请勿重复登录");
        }
        Admin admin = adminService.login(username, password);
        session.setAttribute(StrUtils.LOGIN_ADMIN, admin);
        String beforePage = (String) session.getAttribute(StrUtils.BEFORE_PAGE);
        if (beforePage == null || "".equals(beforePage)) {
            beforePage = "/videos/html/behind/index.html";
        }
        return new Msg(0, beforePage);
    }
}
