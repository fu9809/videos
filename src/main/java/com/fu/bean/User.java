package com.fu.bean;

import lombok.Data;

import java.util.Date;

/**
 * 用户
 * @Author Administrator
 * @Date 2020/1/7 15:43
 */
@Data
public class User {
    private Integer id;
    private String email;
    private String phoneNum;
    private String password;
    private String code;
    private String nickName;
    private String sex;
    private String birthday;
    private String address;
    private String imgUrl;
    private Date createTime;

}
