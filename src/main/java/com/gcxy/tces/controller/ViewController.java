package com.gcxy.tces.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author Rain
 * @date 2019/9/17
 * 只用于视图转发，不处理业务逻辑
 */
@Controller
@RequestMapping("/view")
public class ViewController {

    /**
     * 转发到文件上传界面
     * @return
     */
    @RequestMapping("/upload")
    public String getFileUploadView(){
        return "fileupload";
    }

}
