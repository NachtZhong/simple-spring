package com.nacht.springframework.singleton;

import com.nacht.springframework.BeansException;
import com.nacht.springframework.bean.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * FactoryBean注册表支持, AbstractBeanFactory会继承这个类, 获得注册通过FactoryBean创建的bean的能力
 * @author Nacht
 * Created on 2021/9/7
 */
public class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {

    /**
     * 通过FactoryBean创建的单例bean的集合
     */
    private Map<String, Object> factoryBeanObjectCaches = new ConcurrentHashMap<>();

    /**
     * 通过beanName获取FactoryBean创建的bean对象
     * @param beanName
     * @return
     */
    protected Object getCachedObjectForFactoryBean(String beanName) {
        Object result = factoryBeanObjectCaches.get(beanName);
        return result == NULL_OBJECT ? null : result;
    }

    /**
     * 通过FactoryBean获取其创建的bean对象
     * @param factory
     * @param beanName
     * @return
     */
    protected Object getObjectFromFactoryBean(FactoryBean factory, String beanName) {
        if (factory.isSingleton()) {
            /*如果为单例工厂, 尝试从缓存中获取, 如果缓存中没有, 创建新的bean返回, 并将该bean放入缓存中*/
            Object bean = factoryBeanObjectCaches.get(beanName);
            if(bean == null){
                bean = doGetObjectFromFactoryBean(factory, beanName);
                factoryBeanObjectCaches.put(beanName, bean == null ? NULL_OBJECT : bean);
            }
            return bean == NULL_OBJECT ? null : bean;
        }else{
            /*如果不是单例工厂, 直接创建一个新的bean返回*/
            return doGetObjectFromFactoryBean(factory, beanName);
        }
    }

    /**
     * 通过FactoryBean创建对象并返回
     * @param factory
     * @param beanName
     * @return
     */
    private Object doGetObjectFromFactoryBean(final FactoryBean factory, final String beanName) {
        try {
            return factory.getObject();
        }catch (Exception e){
            throw new BeansException("create bean " + beanName + " by factory bean failed", e);
        }
    }

}
