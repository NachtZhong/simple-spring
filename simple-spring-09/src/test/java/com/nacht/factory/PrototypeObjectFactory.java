package com.nacht.factory;

import com.nacht.springframework.bean.FactoryBean;

/**
 * @author Nacht
 * Created on 2021/9/7
 */
public class PrototypeObjectFactory implements FactoryBean<PrototypeObject> {

    private String factoryName;

    @Override
    public PrototypeObject getObject() throws Exception {
        System.out.println("PrototypeObject创建了一个bean");
        return new PrototypeObject(factoryName);
    }

    @Override
    public Class<?> getObjectType() {
        return PrototypeObject.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }
}
