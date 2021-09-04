package com.nacht.springframework.container;

import com.nacht.springframework.definition.BeanDefinition;
import com.nacht.springframework.singleton.DefaultSingletonBeanRegistry;

/**
 * 容器抽象类
 * @author Nacht
 * Created on 2021/8/30
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {


    /**
     * 根据name获取bean
     * @param name
     * @return
     */
    @Override
    public Object getBean(String name) {
        return doGetBean(name, null);
    }

    /**
     * 根据name获取bean(带参数)
     * @param name
     * @param args
     * @return
     */
    @Override
    public Object getBean(String name, Object... args) {
        return doGetBean(name, args);
    }

    /**
     * 封装一个方法统一处理getBean, 这里用了泛型应该是为了后面提供传入Class来获取bean的方法做准备
     * @param name
     * @param args
     * @param <T>
     * @return
     */
    protected <T> T doGetBean(final String name, final Object[] args){
        Object bean = getSingleton(name);
        if(bean == null){
            bean = createBean(name, getBeanDefinition(name), args);
        }
        return (T) bean;
    }

    /**
     * 根据名字获取BeanDefinition
     * @param name
     * @return
     */
    protected abstract BeanDefinition getBeanDefinition(String name);

    /**
     * 创建bean
     * @param name
     * @param beanDefinition
     * @param args
     */
    protected abstract Object createBean(String name, BeanDefinition beanDefinition, Object[] args);

}
