package com.nacht.factory;

/**
 * @author Nacht
 * Created on 2021/9/7
 */
public class PrototypeObject {

    String factoryName;

    public PrototypeObject(String factoryName) {
        this.factoryName = factoryName;
    }

    @Override
    public String toString() {
        return "PrototypeObject{" +
                "factoryName='" + factoryName + '\'' +
                '}';
    }
}
