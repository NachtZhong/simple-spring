package com.nacht.springframework.context.extension;

import com.nacht.springframework.BeansException;

/**
 * bean实例化的后置处理器, 可以在bean实例化之后对其进行修改
 * 提供两个方法, 一个在bean初始化前调用, 一个在初始化之后调用, 要注意初始化和实例化的区别, 这里的初始化是指init-method
 * @author Nacht
 * Created on 2021/9/4
 */
public interface BeanPostProcessor {

    /**
     * 在 Bean 对象执行初始化方法之前，执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在 Bean 对象执行初始化方法之后，执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;

}
