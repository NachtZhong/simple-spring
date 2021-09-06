package com.nacht.springframework.container;

import com.nacht.springframework.BeansException;
import com.nacht.springframework.definition.BeanDefinition;

/**
 * 集大成者, 提供分析和修改bean以及预先实例化bean操作的接口, 目前只有一个getBeanDefinition方法
 * @author Nacht
 * Created on 2021/9/3
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    /**
     * 根据bean name获取bean definition
     * @param beanName
     * @return
     */
    BeanDefinition getBeanDefinition(String beanName);

    /**
     * 预加载非lazy-init的单例bean
     * @throws BeansException
     */
    void preInstantiateSingletons() throws BeansException;
}
