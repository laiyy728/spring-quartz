package com.laiyy.springquartz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author laiyy
 * @date 2018/6/8 15:05
 * @description 页面管理器
 */
@Controller
@RequestMapping(value = "")
public class ViewController {

    @GetMapping(value = {"/", "index"})
    public String index(){
        return "index";
    }

    @GetMapping("/pro/job/list")
    public String jobList(){
        return "job/list";
    }

}

