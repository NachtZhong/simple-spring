package com.nacht.springframework.container;

/**
 * 可以获取BeanPostProcessor, BeanClassLoader的一个配置化接口
 * @author Nacht
 * Created on 2021/9/3
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory{

    /**
     * bean作用域-单例
     */
    String SCOPE_SINGLETON = "singleton";

    /**
     * bean作用域-原型
     */
    String SCOPE_PROTOTYPE = "prototype";

}
