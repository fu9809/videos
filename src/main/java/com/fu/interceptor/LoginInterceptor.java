package com.fu.interceptor;

import com.fu.bean.Admin;
import com.fu.utils.StrUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  登录拦截器
 * @Author Administrator
 * @Date 2020/1/7 15:07
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        System.out.println(uri);
        Admin user = (Admin) request.getSession().getAttribute(StrUtils.LOGIN_ADMIN);
        if (user == null) {
            String value = request.getHeader("X-Requested-With");
            System.out.println(value);
            // 证明这是一个ajax请求
            if (value != null && value.equals("XMLHttpRequest")) {
                return true;
            } else {
                response.sendRedirect(request.getContextPath() + "/html/behind/login.html");
            }
            return false;
        }
        return true;
    }
}
