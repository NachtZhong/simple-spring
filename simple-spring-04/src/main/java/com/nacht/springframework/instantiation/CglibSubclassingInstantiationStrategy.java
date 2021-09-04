package com.nacht.springframework.instantiation;

import com.nacht.springframework.BeansException;
import com.nacht.springframework.definition.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * bean创建策略-cglib
 * @author Nacht
 * Created on 2021/8/31
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {

    /**
     * 创建bean(要求构造器和args类型要对的上, 不然会报参数不匹配异常)
     * @param name bean name
     * @param beanDefinition bean definition
     * @param ctor 要使用的构造器
     * @param args 初始化用的构造器参数
     * @return
     * @throws BeansException
     */
    @Override
    public Object instantiate(String name, BeanDefinition beanDefinition, Constructor ctor, Object[] args) throws BeansException {
        Class<?> clazz = beanDefinition.getBeanClass();
        try {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(beanDefinition.getBeanClass());
            enhancer.setCallback(new NoOp() {
                @Override
                public int hashCode() {
                    return super.hashCode();
                }
            });
            /*如果ctor为null说明使用默认无参构造器, 调用无参create方法, 反之使用带参create方法创建bean*/
            return ctor == null ? enhancer.create() : enhancer.create(ctor.getParameterTypes(), args);
        }catch (IllegalArgumentException e) {
            throw new BeansException("Failed to instantiate [" + clazz.getName() + "]", e);
        }
    }

}
