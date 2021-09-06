package com.nacht.springframework.bean.aware;

import com.nacht.springframework.container.BeanFactory;

/**
 * 能感知到自身所属BeanFactory的抽象接口
 * @author Nacht
 * Created on 2021/9/6
 */
public interface BeanFactoryAware {

    /**
     * 注入BeanFactory信息
     * @param beanFactory
     */
    void setBeanFactory(BeanFactory beanFactory);

}
