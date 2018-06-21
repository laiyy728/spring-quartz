package com.laiyy.springquartz.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author laiyy
 * @date 2018/6/9 14:05
 * @description 全局基础 Controller，接收一个 Service，并自动注入
 */
public class BaseController<R extends Service>  {

    @Autowired
    protected R service;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

}
