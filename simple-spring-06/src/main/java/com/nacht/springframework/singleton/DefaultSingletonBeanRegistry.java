package com.nacht.springframework.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * 单例bean注册表默认实现类
 * @author Nacht
 * Created on 2021/8/30
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry{

    Map<String, Object> singletonObjects = new HashMap<>();

    /**
     * 根据名字获取单例bean
     * @param name
     * @return
     */
    @Override
    public Object getSingleton(String name) {
        return singletonObjects.get(name);
    }

    /**
     * 将单例对象加入到注册表中
     * @param name
     * @param bean
     */
    public void addSingleton(String name, Object bean){
        singletonObjects.put(name, bean);
    }
}
