package com.nacht.springframework.singleton;

/**
 * 单例bean注册表接口
 * @author Nacht
 * Created on 2021/8/30
 */
public interface SingletonBeanRegistry {

    /**
     * 根据名字获取注册表中对应的单例对象
     * @param name
     * @return
     */
    Object getSingleton(String name);

}
