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
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

    private Map<String, BeanDefinition> definitionMap = new HashMap<>();

    /**
     * 通过name获取bean definition
     * @param name
     * @return
     */
    @Override
    public BeanDefinition getBeanDefinition(String name) {
        BeanDefinition beanDefinition = definitionMap.get(name);
        if(beanDefinition == null){
            throw new BeansException("can not find bean definition of " + name);
        }
        return beanDefinition;
    }

    /**
     * 判断某个bean的definition是否已经注册
     * @param beanName
     * @return
     */
    @Override
    public boolean containsBeanDefinition(String beanName) {
        return definitionMap.containsKey(beanName);
    }

    /**
     * 获取指定类型的所有bean, 封装到map返回
     * @param type
     * @param <T>
     * @return
     */
    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        Map<String, T> resultMap = new HashMap<>();
        /*遍历bean definition集合, 找出所有类为type或者type子类的bean的name, 然后根据name获取到bean*/
        definitionMap.forEach((name, definition) -> {
            Class<?> beanClass = definition.getBeanClass();
            /*isAssignableFrom用来判断beanClass是否为type或者type的直接/间接子类*/
            if(type.isAssignableFrom(beanClass)){
                resultMap.put(name, (T) getBean(name));
            }
        });
        return resultMap;
    }

    /**
     * 获取所有已经注册的bean名称
     * @return
     */
    @Override
    public String[] getBeanDefinitionNames() {
        return definitionMap.keySet().toArray(new String[0]);
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

    /**
     * 预加载非lazy-init的单例bean
     * @throws BeansException
     */
    @Override
    public void preInstantiateSingletons() throws BeansException {
        definitionMap.keySet().forEach(this :: getBean);
    }

}
