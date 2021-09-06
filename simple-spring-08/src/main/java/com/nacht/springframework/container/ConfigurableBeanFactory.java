package com.nacht.springframework.container;

import com.nacht.springframework.context.extension.BeanPostProcessor;
import com.nacht.springframework.singleton.SingletonBeanRegistry;

/**
 * 可以获取BeanPostProcessor, BeanClassLoader的一个配置化接口
 * @author Nacht
 * Created on 2021/9/3
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    /**
     * bean作用域-单例
     */
    String SCOPE_SINGLETON = "singleton";

    /**
     * bean作用域-原型
     */
    String SCOPE_PROTOTYPE = "prototype";

    /**
     * 添加bean的后置处理器
     * @param beanPostProcessor
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 获取ClassLoader
     * @return
     */
    ClassLoader getBeanClassLoader();

    /**
     * 设置ClassLoader
     * @param classLoader
     */
    void setBeanClassLoader(ClassLoader classLoader);
}
