package com.nacht.springframework.instantiation;

import com.nacht.springframework.BeansException;
import com.nacht.springframework.definition.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * bean创建策略-jdk默认
 * @author Nacht
 * Created on 2021/8/31
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    /**
     * 创建bean, 要注意这里构造器必须是public的, 如果想兼容非public的, 要在这里调用构造器对象的setAccessible(true)来获取权限
     * @param name bean name
     * @param beanDefinition bean definition
     * @param ctor 要使用的构造器
     * @param args 初始化用的构造器参数
     * @return
     */
    @Override
    public Object instantiate(String name, BeanDefinition beanDefinition, Constructor ctor, Object[] args){
        Class clazz = beanDefinition.getBeanClass();
        try {
            if (null != ctor) {
                /*有参构造器直接调用创建实例即可*/
                return ctor.newInstance(args);
            } else {
                /*如果没有传入构造器, 默认使用无参构造器*/
                return clazz.getDeclaredConstructor().newInstance();
            }
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new BeansException("Failed to instantiate [" + clazz.getName() + "]", e);
        }
    }

}
