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

    /**
     * 注册容器销毁方法到虚拟机shutdown hook中
     */
    void registerShutdownHook();

    /**
     * 虚拟机关闭时的回调方法, 该方法会被注册到虚拟机的shutdown hook中
     */
    void close();

}
