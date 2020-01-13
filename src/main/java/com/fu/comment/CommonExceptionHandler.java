package com.fu.comment;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常解析器
 * @Author Administrator
 * @Date 2020/1/7 20:17
 */
//@ControllerAdvice
//@Component
public class CommonExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Msg runtimeExceptionHandler(RuntimeException exception) {
        String message = exception.getMessage();
        exception.printStackTrace();
        return new Msg(1, message);
    }
}
