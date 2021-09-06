package com.nacht.springframework.container;

import cn.hutool.core.util.ClassLoaderUtil;
import cn.hutool.core.util.ClassUtil;
import com.nacht.springframework.bean.FactoryBean;
import com.nacht.springframework.context.extension.BeanPostProcessor;
import com.nacht.springframework.definition.BeanDefinition;
import com.nacht.springframework.singleton.DefaultSingletonBeanRegistry;
import com.nacht.springframework.singleton.FactoryBeanRegistrySupport;

import java.util.ArrayList;
import java.util.List;

/**
 * 容器抽象类
 * @author Nacht
 * Created on 2021/8/30
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    /**
     * 类加载器
     */
    private ClassLoader classLoader = ClassLoaderUtil.getClassLoader();

    /**
     * BeanPostProcessor集合
     */
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    /**
     * 根据name获取bean
     * @param name
     * @return
     */
    @Override
    public Object getBean(String name) {
        return doGetBean(name, null);
    }

    /**
     * 根据name获取bean(带参数)
     * @param name
     * @param args
     * @return
     */
    @Override
    public Object getBean(String name, Object... args) {
        return doGetBean(name, args);
    }

    /**
     * 根据bean name和class获取bean, 先简单的强转一下吧
     * @param name
     * @param type
     * @param <T>
     * @return
     */
    @Override
    public <T> T getBean(String name, Class<T> type) {
        return (T) getBean(name);
    }

    /**
     * 封装一个方法统一处理getBean, 这里用了泛型应该是为了后面提供传入Class来获取bean的方法做准备
     * @param name
     * @param args
     * @param <T>
     * @return
     */
    protected <T> T doGetBean(final String name, final Object[] args){
        Object bean = getSingleton(name);
        /*getObjectForBeanInstance方法的作用: 判断bean是否为FactoryBean, 如果是, 调用FactoryBeanRegistrySupport的方法来获取bean, 如果不是直接返回当前bean*/
        if(bean == null) {
            bean = createBean(name, getBeanDefinition(name), args);
        }
        return (T) getObjectForBeanInstance(bean, name);
    }

    /**
     * 对bean进行判断, 如果是FactoryBean, 调用FactoryBeanRegistrySupport的方法获取bean, 如果不是FactoryBean, 直接返回
     * 还有一种特殊情况, 用户就是要获取FactoryBean, 那么约定在bean前面加上&, 就是获取FactoryBean而不是它生产出来的bean, 不过这里不做实现
     * @param bean
     * @param beanName
     * @return
     */
    private Object getObjectForBeanInstance(Object bean, String beanName){
        /*如果这个bean不是工厂bean, 那么就直接返回就可以了*/
        if(!(bean instanceof FactoryBean)){
            return bean;
        }
        /*如果这个是工厂bean, 说明真正要获取的bean在FactoryBeanRegistrySupport里面工厂创建的bean的集合中, 调用相关方法获取*/
        Object realBean = getCachedObjectForFactoryBean(beanName);
        /*如果没有获取到, 将工厂bean传进去进行创建*/
        if(realBean == null){
            realBean = getObjectFromFactoryBean((FactoryBean) bean, beanName);
        }
        return realBean;
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
     * @param args
     */
    protected abstract Object createBean(String name, BeanDefinition beanDefinition, Object[] args);

    /**
     * 添加BeanPostProcessor到集合中
     * @param beanPostProcessor
     */
    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        /*简单去重, remove old position, if any*/
        beanPostProcessors.remove(beanPostProcessor);
        beanPostProcessors.add(beanPostProcessor);
    }

    /**
     * 获取所有的BeanPostProcessor
     * @return
     */
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return beanPostProcessors;
    }

    /**
     * 获取ClassLoader
     * @return
     */
    @Override
    public ClassLoader getBeanClassLoader() {
        return classLoader;
    }

    /**
     * 设置ClassLoader
     * @param classLoader
     */
    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }
}
