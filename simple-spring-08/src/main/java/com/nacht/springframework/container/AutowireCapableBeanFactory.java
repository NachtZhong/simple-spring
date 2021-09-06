package com.nacht.springframework.container;

import com.nacht.springframework.BeansException;

/**
 * 可以自动化进行配置bean的接口, 目前这一步未实现
 * @author Nacht
 * Created on 2021/9/3
 */
public interface AutowireCapableBeanFactory extends BeanFactory{

    /**
     * 执行BeanPostProcessor前置方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 执行BeanPostProcessor后置方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsAfterInitialization(Object bean, String beanName) throws BeansException;

}
