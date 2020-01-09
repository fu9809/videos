package com.fu.controller;

import com.fu.utils.LoggerUtils;
import com.fu.utils.StrUtils;
import com.fu.vcode.Captcha;
import com.fu.vcode.GifCaptcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 验证码控制器
 * @Author Administrator
 * @Date 2020/1/9 19:54
 */
@Controller
public class VcodeController {
    /**
     * 获取验证码（Gif版本）
     */
    @RequestMapping(value="/getGifCode")
    public void getGifCode(HttpServletResponse response, HttpServletRequest request){
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");
            HttpSession session = request.getSession(true);
            /**
             * gif格式动画验证码
             * 宽，高，位数。
             */
            Captcha captcha = new GifCaptcha(146,33,4);
            //输出
            captcha.out(response.getOutputStream());
            //存入Session
            session.setAttribute(StrUtils.LOGIN_CODE, captcha.text().toLowerCase());
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(),e, "获取验证码异常：%s",e.getMessage());
        }
    }
}
