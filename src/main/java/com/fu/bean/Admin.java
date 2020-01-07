package com.fu.bean;

import lombok.Data;

/**
 * 管理员
 * @Author Administrator
 * @Date 2020/1/7 15:47
 */
@Data
public class Admin {
    private Integer id;
    private String username;
    private String password;
    private String roles;

}
