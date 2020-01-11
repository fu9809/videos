package com.fu.controller;

import com.fu.bean.User;
import com.fu.comment.Msg;
import com.fu.service.UserService;
import com.fu.utils.BCryptUtils;
import com.fu.utils.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
        String path = "/static/upload/";
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
        user.setImgUrl(path + filename);
        user.setCreateTime(new Date());
        int num = userService.addUser(user);
        return new Msg(0, num);
    }

}
