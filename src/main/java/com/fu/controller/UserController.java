package com.fu.controller;

import com.fu.bean.User;
import com.fu.comment.Msg;
import com.fu.service.UserService;
import com.fu.utils.BCryptUtils;
import com.fu.utils.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @Author Administrator
 * @Date 2020/1/7 16:05
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register.do")
    @ResponseBody
    public Msg register(@RequestParam MultipartFile file,
                        User user, String password2, String captcha, HttpSession session) {
        if (!user.getPassword().equals(password2)) {
            return new Msg(1, "两次密码不一致");
        }
        String captchaCode = (String) session.getAttribute(StrUtils.LOGIN_CODE);
        if (!captchaCode.equals(captcha)) {
            return new Msg(1, "验证码输入错误");
        }
        // 密码加密
        String pass = BCryptUtils.encryption(password2);
        user.setPassword(pass);

        String filename = file.getOriginalFilename();
        String path = "static/upload/";
        String dir = session.getServletContext().getRealPath("") + path;
        File dirFile = new File(dir);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File newFile = new File(dir, filename);
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
            return new Msg(1, "文件上传时出错了");
        }
        user.setImgUrl("/videos/" + path + filename);
        user.setCreateTime(new Date());
        int num = userService.addUser(user);
        return new Msg(0, num);
    }

    @PostMapping("/login.do")
    @ResponseBody
    public Msg login(String code, String password, String captcha, HttpSession session) {
        String captchaCode = (String) session.getAttribute(StrUtils.LOGIN_CODE);
        if (!captchaCode.equals(captcha)) {
            return new Msg(1, "验证码错误");
        }
        String emailExg = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        String phoneExg = "^[1][3578]\\d{9}$";
        User user = null;
        if (code.matches(emailExg)) {   // 邮箱
            user = userService.getUserByEmail(code);
        } else if (code.matches(phoneExg)) {    // 手机号
            user = userService.getUserByPhone(code);
        } else {    // 账号
            user = userService.getUserByCode(code);
        }

        if (user == null) {
            return new Msg(1, "用户未注册");
        }

        if (BCryptUtils.verify(password, user.getPassword())) {
            session.setAttribute(StrUtils.LOGIN_USER, user);
            String beforePage = (String) session.getAttribute(StrUtils.BEFORE_PAGE);
            if (beforePage == null || "".equals(beforePage)) {
                beforePage = "/videos/html/before/index.html";
            }
            return new Msg(0, beforePage);
        } else {
            return new Msg(1, "账号或密码错误");
        }
    }

    @GetMapping("/getUser.do")
    @ResponseBody
    public Msg getUser(HttpSession session) {
        User user = (User) session.getAttribute(StrUtils.LOGIN_USER);
        if (user != null) {
            return new Msg(0, user);
        } else {
            return new Msg(1, "未登录");
        }

    }

}
