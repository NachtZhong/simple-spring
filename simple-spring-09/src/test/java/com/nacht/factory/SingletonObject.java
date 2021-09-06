package com.nacht.factory;

/**
 * @author Nacht
 * Created on 2021/9/7
 */
public class SingletonObject {

    String factoryName;

    public SingletonObject(String factoryName) {
        this.factoryName = factoryName;
    }

    @Override
    public String toString() {
        return "SingletonObject{" +
                "factoryName='" + factoryName + '\'' +
                '}';
    }
}
