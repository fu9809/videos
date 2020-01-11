package com.test;

import com.fu.utils.BCryptUtils;

/**
 * @Author Administrator
 * @Date 2020/1/11 15:46
 */
public class Test {

    public static void main(String[] args) {
        String encryption = BCryptUtils.encryption("123");
        System.out.println(encryption);

        System.out.println(BCryptUtils.verify("123", encryption));
        System.out.println(BCryptUtils.verify("124", encryption));
    }
}
