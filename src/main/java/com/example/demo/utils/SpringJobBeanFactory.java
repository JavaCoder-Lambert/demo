package com.example.demo.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author lizhijiang
 * @Version
 * @Description
 * @CreateTime 2021年11月10日 19:44
 */
@Component
public class SpringJobBeanFactory implements ApplicationContextAware {


    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringJobBeanFactory.applicationContext=applicationContext;

    }
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) throws BeansException {
        if (applicationContext == null){
            return null;
        }
        return (T)applicationContext.getBean(name);
    }
    /**
     * 使用demo
     * //TypeSetErpService typeSetErpServ = SpringJobBeanFactory.getBean("typeSetErpServiceImpl");
     */

}
