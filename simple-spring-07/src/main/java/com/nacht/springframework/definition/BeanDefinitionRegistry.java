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

    /**
     * 根据bean name获取bean definition
     * @param beanName
     * @return
     */
    BeanDefinition getBeanDefinition(String beanName);

    /**
     * 判断某个bean的definition是否已经注册
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 获取所有已经注册的bean名称
     * @return
     */
    String[] getBeanDefinitionNames();

}
