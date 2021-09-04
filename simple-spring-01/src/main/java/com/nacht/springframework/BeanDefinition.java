package com.nacht.springframework;

import lombok.SneakyThrows;

/**
 * 存储bean定义
 * @author Nacht
 * Created on 2021/8/30
 */
public class BeanDefinition {

    /**
     * 存储bean的class信息
     */
    private Class clazz;

    /**
     * 存储bean
     */
    private Object bean;

    /**
     * 通过构造函数传入类信息
     * @param clazz
     */
    public BeanDefinition(Class clazz){
        this.clazz = clazz;
    }

    /**
     * 获取bean
     * 如果为空, 创建一个, 如果有了, 直接返回(目前实现还是不安全的单例, 注意了)
     * @return
     */
    @SneakyThrows
    public Object getBean(){
        if(bean == null){
            bean = clazz.getConstructor().newInstance();
        }
        return bean;
    }
}
