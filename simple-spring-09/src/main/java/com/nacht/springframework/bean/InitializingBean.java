package com.nacht.springframework.bean;

/**
 * 需要自定义初始化方法的bean的抽象接口
 * @author Nacht
 * Created on 2021/9/6
 */
public interface InitializingBean {

    /**
     * 顾名思义, bean实例化, 并且属性填充完成后会被调用
     */
    void afterPropertiesSet();

}
