package com.nacht.springframework.container;


/**
 * 抽象容器接口
 * @author Nacht
 * Created on 2021/8/30
 */
public interface BeanFactory {

    /**
     * 根据bean name获取bean
     * @param name
     * @return
     */
    Object getBean(String name);

    /**
     * 根据bean name和class获取bean
     * @param name
     * @param type
     * @param <T>
     * @return
     */
    <T> T getBean(String name, Class<T> type);

    /**
     * 根据bean name和构造函数参数获取bean
     * @param name
     * @param args
     * @return
     */
    Object getBean(String name, Object... args);

}
