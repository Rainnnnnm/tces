package com.gcxy.tces.controller.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * @author Rain
 * @date 2019/10/8
 *
 * 全局异常处理类
 * ControllerAdvice controller通知类，用于增强controller方法
 */
@ControllerAdvice
public class GlobalExceptionResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionResolver.class);

    /**
     * 捕获UnauthorizedException并进行处理
     * @param e 捕获的异常
     * @return 重定向到无权限界面
     */
    @ExceptionHandler(UnauthorizedException.class)
    public String unauthorizedException(Exception e){
        LOGGER.debug("该操作未授权");
        return "redirect:/view/unauthorized.html";
    }
}
