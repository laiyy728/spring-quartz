package com.laiyy.springquartz.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author laiyy
 * @date 2018/6/9 14:05
 * @description
 */
public class BaseController<R extends Service>  {

    @Autowired
    protected R service;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

}
