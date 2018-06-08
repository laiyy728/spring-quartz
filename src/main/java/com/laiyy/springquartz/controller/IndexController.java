package com.laiyy.springquartz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author laiyy
 * @date 2018/6/8 22:08
 * @description
 */
@Controller
public class IndexController {

    @GetMapping(value = {"/", "index"})
    public String index(){
        return "index";
    }

}
