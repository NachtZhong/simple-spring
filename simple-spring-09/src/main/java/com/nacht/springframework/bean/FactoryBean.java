package com.nacht.springframework.bean;

/**
 * 工厂类型的bean抽象接口
 * @author Nacht
 * Created on 2021/9/7
 */
public interface FactoryBean<T> {

    /**
     * 获取工厂bean生产的对象
     * @return
     * @throws Exception
     */
    T getObject() throws Exception;

    /**
     * 获取工厂bean生产的对象类型
     * @return
     */
    Class<?> getObjectType();

    /**
     * 工厂bean生产的对象是否为单例bean
     * @return
     */
    boolean isSingleton();
}
