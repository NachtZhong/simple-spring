package com.nacht.springframework.container;

import java.util.Map;

/**
 * 扩展了BeanFactory的接口, 将BeanDefinitionRegistry接口的一些方法也整合了进来, 同时增加了通过Type获取所有Bean的方法(批量获取方法)
 * @author Nacht
 * Created on 2021/9/3
 */
public interface ListableBeanFactory extends BeanFactory{

    /**
     * 获取某种类型的所有bean, 封装到map中返回
     * @param type
     * @param <T>
     * @return
     */
    <T> Map<String, T> getBeansOfType(Class<T> type);

    /**
     * 获取所有已经注册的bean名称
     * @return
     */
    String[] getBeanDefinitionNames();

}
