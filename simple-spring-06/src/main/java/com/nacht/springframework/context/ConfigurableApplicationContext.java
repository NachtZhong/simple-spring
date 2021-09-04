package com.nacht.springframework.context;

import com.nacht.springframework.BeansException;

/**
 * ApplicationContext的扩展接口
 * @author Nacht
 * Created on 2021/9/4
 */
public interface ConfigurableApplicationContext extends ApplicationContext{

    /**
     * 刷新容器
     * @throws BeansException
     */
    void refresh() throws BeansException;

}
