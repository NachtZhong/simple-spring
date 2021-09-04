package com.nacht.springframework.definition;

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
     * 通过构造函数传入类信息
     * @param clazz
     */
    public BeanDefinition(Class clazz){
        this.clazz = clazz;
    }

    /**
     * 获取class
     * @return
     */
    public Class getBeanClass() {
        return clazz;
    }

    /**
     * 设置class
     * @param clazz
     */
    public void setBeanClass(Class clazz) {
        this.clazz = clazz;
    }
}
