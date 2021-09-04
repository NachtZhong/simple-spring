package com.nacht.springframework.container;

import com.nacht.springframework.definition.BeanDefinition;
import com.nacht.springframework.singleton.DefaultSingletonBeanRegistry;

/**
 * @author Nacht
 * Created on 2021/8/30
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    /**
     * 根据name获取bean
     * @param name
     * @return
     */
    @Override
    public Object getBean(String name) {
        Object bean = getSingleton(name);
        if(bean == null){
            bean = createBean(name, getBeanDefinition(name));
            addSingleton(name, bean);
        }
        return bean;
    }

    /**
     * 根据名字获取BeanDefinition
     * @param name
     * @return
     */
    protected abstract BeanDefinition getBeanDefinition(String name);

    /**
     * 创建bean
     * @param name
     * @param beanDefinition
     */
    protected abstract Object createBean(String name, BeanDefinition beanDefinition);

}
