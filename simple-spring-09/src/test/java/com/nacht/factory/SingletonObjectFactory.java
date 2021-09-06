package com.nacht.factory;

import com.nacht.springframework.bean.FactoryBean;

/**
 * @author Nacht
 * Created on 2021/9/7
 */
public class SingletonObjectFactory implements FactoryBean<SingletonObject> {

    private String factoryName;

    @Override
    public SingletonObject getObject() throws Exception {
        System.out.println("SingletonObjectFactory创建了一个bean");
        return new SingletonObject(factoryName);
    }

    @Override
    public Class<?> getObjectType() {
        return SingletonObject.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }
}
