package com.nacht.springframework.bean.aware;

/**
 * 能感知到使用的ClassLoader的抽象接口
 * @author Nacht
 * Created on 2021/9/6
 */
public interface BeanClassLoaderAware extends Aware{

    /**
     * 注入ClassLoader信息
     * @param beanClassLoader
     */
    void setBeanClassLoader(ClassLoader beanClassLoader);

}
