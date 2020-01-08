package com.fu.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.FormContentFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.EnumSet;

/**
 * web根容器配置类
 *      tomcat启动的时候会自动加载实现了 WebApplicationInitializer 接口的类，运行 onStartup 方法
 */
public class RootConfig implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        // 向web容器中注册 spring 的配置和springMVC的配置
        ac.register(AppConfig.class, WebmvcConfig.class);

        // 配置dispatcherServlet，添加web容器的配置
        DispatcherServlet dispatcherServlet = new DispatcherServlet(ac);
//        hiddenHttpMethodFilter.
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcherServlet", dispatcherServlet);
        // 容器启动的时候加载该servlet
        servlet.setLoadOnStartup(1);
        // 配置dispatcherServlet的映射路径
        servlet.addMapping("/");
        // 配置支持 PUT、DELETE 提交方式
        servletContext.addFilter("HiddenHttpMethodFilter", new HiddenHttpMethodFilter())
                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
        servletContext.addFilter("FormContentFilter", FormContentFilter.class)
                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
    }
}