package com.nacht.springframework.singleton;

import com.nacht.springframework.BeansException;
import com.nacht.springframework.bean.DisposableBean;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 单例bean注册表默认实现类
 * @author Nacht
 * Created on 2021/8/30
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry{

    /**
     * bean集合
     */
    private final Map<String, Object> singletonObjects = new HashMap<>();

    /**
     * 需要执行销毁方法的bean集合
     */
    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

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

    /**
     * 将需要执行销毁方法的bean添加到集合中
     * @param name
     */
    public void registerDisposableBean(String name, DisposableBean disposableBean){
        this.disposableBeans.put(name, disposableBean);
    }

    /**
     * 销毁时执行所有disposableBean的销毁方法
     */
    public void destroySingletons(){
        Object[] beanNames = disposableBeans.keySet().toArray();
        for(int i = 0; i < beanNames.length; i++){
            Object beanName = beanNames[i];
            DisposableBean disposableBean = disposableBeans.remove(String.valueOf(beanName));
            try {
                disposableBean.destroy();
            }catch (Exception e){
                throw new BeansException("an error occurs while invoking destroy method of bean " + beanName, e);
            }
        }
    }
}
