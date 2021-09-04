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

}
