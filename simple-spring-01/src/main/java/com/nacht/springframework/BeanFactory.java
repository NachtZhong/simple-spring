package com.nacht.springframework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 容器类
 * @author Nacht
 * Created on 2021/8/30
 */
public class BeanFactory {

    /**
     * 存储bean definition的map
     */
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    /**
     * 注册bean definition到容器中
     * @param name
     * @param beanDefinition
     */
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition){
        beanDefinitionMap.put(name, beanDefinition);
    }

    /**
     * 通过名字获取bean
     * @param name
     */
    public Object getBean(String name){
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        return beanDefinition == null ? null : beanDefinition.getBean();
    }

}
