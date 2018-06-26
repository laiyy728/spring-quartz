package com.laiyy.springquartz.util;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author wh
 */
@Service
@Lazy(false)
public class SpringUtil implements ApplicationContextAware,
        DisposableBean {
	
	private static final Logger logger = LoggerFactory.getLogger(SpringUtil.class);
	
	
	private static ApplicationContext applicationContext;
	
	public static ApplicationContext getApplicationContext(){
		assertContetInject();
		return applicationContext;
	}
	

	@Override
	public void destroy() throws Exception {
		SpringUtil.clearHodler();
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		
		if(SpringUtil.applicationContext!=null){
			logger.info("SpringHoderContext里面的ApplicationContext被覆盖，原有的applicationContext值为："
					+ SpringUtil.applicationContext);
		}
		SpringUtil.applicationContext = arg0;
	}
	
	
	
	private static void clearHodler(){
		if(logger.isDebugEnabled()){
			logger.info("清除SpringContextHolder中的ApplicationContext:"+applicationContext);
		}
		applicationContext = null;
	}
	
	/**
	 * 检查 applicationContext不为空
	 */
	private static void assertContetInject(){
		Validate.validState(applicationContext != null,
				"applicaitonContext属性未注入, 请在applicationContext.xml中定义SpringContextHolder.");
	}
	
	/**
	 * 获取根目录
	 * @return
	 */
	public  static String getRootPath(){
		String path="";
		try {
			path = getApplicationContext().getResource("").getFile().getAbsolutePath();
		} catch (IOException e) {
			logger.warn("获取系统根目录失败！");
		}
		return path;
	}
	/**
	 * 
	 * @param ml 目录名
	 * @return
	 */
	public static String getPath(String ml){
		String mulu ="/"+ml;
		String path="";
		try {
			path = getApplicationContext().getResource(mulu).getFile().getAbsolutePath();
		} catch (IOException e) {
			logger.warn("获取目录失败！");
		}
		return path;
		
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name){
		assertContetInject();
		return (T) applicationContext.getBean(name);
	}

}
