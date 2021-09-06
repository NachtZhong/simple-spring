package com.nacht.springframework.bean.aware;

import com.nacht.springframework.BeansException;
import com.nacht.springframework.context.ApplicationContext;
import com.nacht.springframework.context.extension.BeanPostProcessor;

/**
 * 用来做ApplicationContext Aware上下文感知信息注入的BeanPostProcessor
 * @author Nacht
 * Created on 2021/9/6
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    /**
     * 自身需要保存ApplicationContext用来传递
     */
    private ApplicationContext applicationContext;

    /**
     * 构造函数中传入上下文ApplicationContext实例
     * @param applicationContext
     */
    public ApplicationContextAwareProcessor(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    /**
     * 考虑到可能在init方法中就会使用到感知的ApplicationContext, 将感知信息注入的过程放在前置处理器
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof ApplicationContextAware){
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    /**
     * 后置处理器不做任何处理
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
