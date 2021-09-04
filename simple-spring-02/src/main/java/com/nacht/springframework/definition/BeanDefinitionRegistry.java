package com.nacht.springframework.definition;

/**
 * BeanDefinition的注册表
 * @author Nacht
 * Created on 2021/8/30
 */
public interface BeanDefinitionRegistry {

    /**
     * 注册Bean Definition, 为什么要用beanName作为key仍然有疑问
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

}
