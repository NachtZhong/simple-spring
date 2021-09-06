package com.nacht.springframework.definition;

import com.nacht.springframework.container.ConfigurableBeanFactory;
import lombok.SneakyThrows;

/**
 * 存储bean定义
 * @author Nacht
 * Created on 2021/8/30
 */
public class BeanDefinition {

    /**
     * 单例scope配置常量
     */
    private final String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;

    /**
     * 原型scope配置常量
     */
    private final String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;

    /**
     * 存储bean的class信息
     */
    private Class clazz;

    /**
     * 属性集合
     */
    private PropertyValues propertyValues;

    /**
     * 初始化方法名
     */
    private String initMethodName;

    /**
     * 销毁方法名
     */
    private String destroyMethodName;

    /**
     * 默认的scope为单例
     */
    private String scope = SCOPE_SINGLETON;

    /**
     * 是否为单例, 默认true
     */
    private boolean singleton = true;

    /**
     * 是否为原型, 默认false
     */
    private boolean prototype = false;



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

    /**
     * 获取初始化方法名
     * @return
     */
    public String getInitMethodName() {
        return initMethodName;
    }

    /**
     * 设置初始化方法名
     * @param initMethodName
     */
    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    /**
     * 获取销毁方法名
     * @return
     */
    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    /**
     * 设置销毁方法名
     * @param destroyMethodName
     */
    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }

    /**
     * 设置scope
     * @param scope
     */
    public void setScope(String scope){
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }

    /**
     * 是否为单例
     * @return
     */
    public boolean isSingleton() {
        return singleton;
    }

    /**
     * 是否为原型
     * @return
     */
    public boolean isPrototype() {
        return prototype;
    }
}
