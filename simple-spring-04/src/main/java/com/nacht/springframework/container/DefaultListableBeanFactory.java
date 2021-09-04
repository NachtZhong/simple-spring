package com.nacht.springframework.container;

import com.nacht.springframework.BeansException;
import com.nacht.springframework.definition.BeanDefinition;
import com.nacht.springframework.definition.BeanDefinitionRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * 容器具体实现类
 * @author Nacht
 * Created on 2021/8/30
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    private Map<String, BeanDefinition> definitionMap = new HashMap<>();

    /**
     * 通过name获取bean definition
     * @param name
     * @return
     */
    @Override
    protected BeanDefinition getBeanDefinition(String name) {
        BeanDefinition beanDefinition = definitionMap.get(name);
        if(beanDefinition == null){
            throw new BeansException("can not find bean definition of " + name);
        }
        return beanDefinition;
    }


    /**
     * 注册bean definition
     * @param beanName
     * @param beanDefinition
     */
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        definitionMap.put(beanName, beanDefinition);
    }
}
