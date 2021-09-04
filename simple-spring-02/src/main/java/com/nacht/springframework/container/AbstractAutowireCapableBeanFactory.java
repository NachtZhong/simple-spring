package com.nacht.springframework.container;

import com.nacht.springframework.BeansException;
import com.nacht.springframework.definition.BeanDefinition;

/**
 * 容器抽象类
 * @author Nacht
 * Created on 2021/8/30
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    /**
     * 创建bean
     * @param name
     * @param beanDefinition
     * @return
     */
    @Override
    protected Object createBean(String name, BeanDefinition beanDefinition) {
        Object bean = null;
        try{
            /*通过BeanDefinition创建实例*/
            bean = beanDefinition.getBeanClass().getConstructor().newInstance();
            /*将实例对象添加到单例注册表中*/
            addSingleton(name, bean);
            return bean;
        }catch(Exception e){
            throw new BeansException("create bean failed", e);
        }
    }
}
