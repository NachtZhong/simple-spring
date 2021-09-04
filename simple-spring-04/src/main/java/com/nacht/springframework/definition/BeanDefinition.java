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
     * 属性集合
     */
    private PropertyValues propertyValues;

    /**
     * 通过构造函数传入类信息
     * @param clazz
     */
    public BeanDefinition(Class clazz){
        this.clazz = clazz;
        this.propertyValues = new PropertyValues();
    }

    /**
     * 通过构造函数传入类信息和属性信息
     * @param clazz
     * @param propertyValues
     */
    public BeanDefinition(Class clazz, PropertyValues propertyValues){
        this.clazz = clazz;
        this.propertyValues = propertyValues == null ? new PropertyValues() : propertyValues;
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

    /**
     * 获取bean的属性信息
     * @return
     */
    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    /**
     * 设置bean的属性信息
     * @param propertyValues
     */
    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
