package com.fu.comment;

import lombok.Data;

/**
 * @Author Administrator
 * @Date 2020/1/7 17:33
 */
@Data
public class Msg {
    private Integer code = 0;
    private Object data;

    public Msg() {}

    public Msg(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }


}
