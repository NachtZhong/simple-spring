package com.nacht.springframework.context.extension;

import com.nacht.springframework.BeansException;
import com.nacht.springframework.container.ConfigurableListableBeanFactory;

/**
 * bean factory后置处理器, 在bean definition全部注册完成, bean实例化之前调用
 * @author Nacht
 * Created on 2021/9/4
 */
public interface BeanFactoryPostProcessor {

    /**
     * 所有bean definition注册完成后执行该方法, 可以对bean definition进行修改
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
