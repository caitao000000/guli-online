package com.bairui.anychat.servicebase.exception;

import com.bairui.common.utils.ExceptionUtil;
import com.bairui.common.utils.Resp;
import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    // 全局异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Resp error(Exception e){
        e.printStackTrace();
        return Resp.error().message("系统异常");
    }

    //特殊异常处理
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Resp error(ArithmeticException e){
        e.printStackTrace();
        return Resp.error().message("执行了自定义异常");
    }

    // 自定义异常处理
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public Resp error(GuliException e){
        log.error(ExceptionUtil.getMessage(e));
        e.printStackTrace();
        return Resp.error().message(e.getMsg()).code(e.getCode());
    }
}
