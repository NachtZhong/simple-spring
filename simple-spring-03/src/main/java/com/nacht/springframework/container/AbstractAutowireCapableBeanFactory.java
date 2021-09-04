package com.nacht.springframework.container;

import com.nacht.springframework.BeansException;
import com.nacht.springframework.definition.BeanDefinition;
import com.nacht.springframework.instantiation.CglibSubclassingInstantiationStrategy;
import com.nacht.springframework.instantiation.InstantiationStrategy;

import java.lang.reflect.Constructor;
import java.util.Arrays;

/**
 * 容器抽象类
 * @author Nacht
 * Created on 2021/8/30
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    /**
     * 默认使用cglib策略来进行bean的创建
     */
    InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    /**
     * 创建bean
     * @param name
     * @param beanDefinition
     * @param args
     * @return
     */
    @Override
    protected Object createBean(String name, BeanDefinition beanDefinition, Object[] args) {
        Object bean = null;
        try{
            /*通过BeanDefinition创建实例*/
            bean = createBeanInstance(name, beanDefinition, args);
            /*将实例对象添加到单例注册表中*/
            addSingleton(name, bean);
            return bean;
        }catch(Exception e){
            throw new BeansException("create bean failed", e);
        }
    }

    /**
     * 封装一个方法来进行bean的创建具体逻辑
     * @param name
     * @param beanDefinition
     * @param args
     * @return
     */
    protected Object createBeanInstance(String name, BeanDefinition beanDefinition, Object[] args) throws BeansException{
        /*根据参数类型找到对应的constructor*/
        /*先尝试根据args匹配对应的constructor, 如果找不到, 就抛出异常*/
        Constructor<?> ctor = null;
        for(Constructor<?> c : beanDefinition.getBeanClass().getDeclaredConstructors()){
            /*带参的情况 -> args不为null, 构造函数的参数类型数组长度和args长度相等, 实际应该还要判断每一个参数的类型是否相等, 这里不做判断, 交给下一层创建的时候cglib和jdk匹配, 如果不匹配抛异常即可*/
            if(args != null && args.length == c.getParameterTypes().length){
                ctor = c;
                break;
            }
        }
        /*传入的参数不为空但是没匹配上, 说明没有对应的有参构造器, 抛出异常*/
        if(args != null && ctor == null){
            throw new BeansException("can not find match constructor in class " + beanDefinition.getBeanClass().toString() + " with parameters " + Arrays.toString(args));
        }
        return instantiationStrategy.instantiate(name, beanDefinition, ctor, args);
    }

    /**
     * 获取bean创建策略
     * @return
     */
    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    /**
     * 设置bean创建策略
     * @param instantiationStrategy
     */
    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }


}
