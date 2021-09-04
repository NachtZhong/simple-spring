package com.nacht.springframework.instantiation;

import com.nacht.springframework.BeansException;
import com.nacht.springframework.definition.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 初始化bean的抽象接口
 * @author Nacht
 * Created on 2021/8/31
 */
public interface InstantiationStrategy {

    /**
     * 实例化bean
     * @param name bean name
     * @param beanDefinition bean definition
     * @param ctor 要使用的构造器
     * @param args 初始化用的构造器参数
     * @return
     */
    Object instantiate(String name, BeanDefinition beanDefinition, Constructor ctor, Object[] args) throws BeansException;

}
