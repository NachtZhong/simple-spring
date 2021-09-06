package com.nacht.springframework.bean.aware;

/**
 * 能感知自身beanName的抽象接口
 * @author Nacht
 * Created on 2021/9/6
 */
public interface BeanNameAware extends Aware{

    /**
     * 注入beanName信息
     * @param beanName
     */
    void setBeanName(String beanName);

}
