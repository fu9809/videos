package com.fu.main;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

/**
 *  Main线程启动类
 * @author Administrator
 */
public class Main {
    public static void main(String[] args) {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        // contextPath：启动时路径资源名。
        // docBase：webapp的目录。目前只能写绝对路径，解决方法暂未找到
        tomcat.addWebapp("/videos", "H:\\workspace\\videos\\src\\main\\webapp");
        try {
            // 启动
            tomcat.start();
            // 线程阻塞，不阻塞的话，tomcat启动完成之后会关闭
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}