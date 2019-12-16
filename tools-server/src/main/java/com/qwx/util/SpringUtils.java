package com.qwx.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public final class SpringUtils implements BeanFactoryPostProcessor, ResourceLoaderAware{  
	  
    private static ConfigurableListableBeanFactory beanFactory; // Spring应用上下文环境  
    private static ResourceLoader resourceLoader;  
  
    public void setResourceLoader(ResourceLoader resourceLoader) {  
        SpringUtils.resourceLoader = resourceLoader;  
    }  

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {  
        SpringUtils.beanFactory = beanFactory;  
    } 
  
    /** 
     * 1. classpath: classpath:com/myapp/config.xml , 加载classpath下的资源 
     * 2. file: file:/data/config.xml Loaded as a URL, 加载文件系统下的资源 
     * 3. http: http://myserver/logo.png Loaded as a URL. 加载url下面的资源 
     * 4. (none) /data/config.xml , 根据当前的applicationContext的类型来加载资源 
     *  
     * @param location 
     * @return 
     */  
    public static Resource getResource(String location) {  
        return resourceLoader.getResource(location);  
    }  
  
    /** 
     * 获取对象 
     *  
     * @param name 
     * @return Object 一个以所给名字注册的bean的实例 
     * @throws org.springframework.beans.BeansException 
     *  
     */  
    @SuppressWarnings("unchecked")  
    public static <T> T getBean(String name) throws BeansException {  
        return (T) beanFactory.getBean(name);  
    }  
    
    @SuppressWarnings("unchecked")  
    public static <T> T getBean(String name,Class<?> clazz) throws BeansException {  
        return (T) beanFactory.getBean(name,clazz);  
    }      
}