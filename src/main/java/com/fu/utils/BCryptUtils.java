package com.fu.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * 密码加密，验证工具类
 * @Author Administrator
 * @Date 2020/1/11 15:58
 */
public class BCryptUtils {

    /**
     * 加密
     * @param password 密码
     * @return 返回加密后的结果
     */
    public static String encryption(String password) {
        return BCrypt.withDefaults().hashToString(10, password.toCharArray());
    }

    public static boolean verify(String beforePass, String afterPass) {
        BCrypt.Result result = BCrypt.verifyer().verify(beforePass.toCharArray(), afterPass);
        return result.verified;
    }
}
