package com.fu.interceptor;

import com.fu.utils.StrUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *  登录拦截器，返回登录前的页面
 * @Author Administrator
 * @Date 2020/1/7 15:07
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();   // 请求的链接
        System.out.println(uri);
        // /videos/admin/login.do
        String referer = request.getHeader("referer"); // 发送请求的所在的页面的全路径
        System.out.println(referer);
        // http://localhost:8080/videos/html/behind/login.html

        HttpSession session = request.getSession(true);

        if (uri.contains("login") || uri.contains("register")) {
            return true;
        }

        if (session.getAttribute(StrUtils.LOGIN_ADMIN) != null) {
            return true;
        } else if (session.getAttribute(StrUtils.LOGIN_USER) != null) {
            if (uri.contains("admin") || uri.contains("behind")) {
                response.sendRedirect(request.getContextPath() + "/html/error.html");
                return false;
            } else {
                return true;
            }
        } else if (uri.contains("admin") || uri.contains("behind")) {
            if (referer == null) {
                session.setAttribute(StrUtils.BEFORE_PAGE, uri);
            } else {
                session.setAttribute(StrUtils.BEFORE_PAGE, referer);
            }
            response.sendRedirect(request.getContextPath() + "/html/behind/login.html");
            return false;
        } else if (uri.contains("user") || uri.contains("before")) {
            if (referer == null) {
                session.setAttribute(StrUtils.BEFORE_PAGE, uri);
            } else {
                session.setAttribute(StrUtils.BEFORE_PAGE, referer);
            }
            response.sendRedirect(request.getContextPath() + "/html/before/login.html");
            return false;
        }
        return true;
    }
}
