package com.nacht.springframework.container;

import cn.hutool.core.util.StrUtil;
import com.nacht.springframework.BeansException;
import com.nacht.springframework.bean.DisposableBean;
import com.nacht.springframework.bean.DisposableBeanAdapter;
import com.nacht.springframework.bean.InitializingBean;
import com.nacht.springframework.bean.aware.Aware;
import com.nacht.springframework.bean.aware.BeanClassLoaderAware;
import com.nacht.springframework.bean.aware.BeanFactoryAware;
import com.nacht.springframework.bean.aware.BeanNameAware;
import com.nacht.springframework.context.extension.BeanPostProcessor;
import com.nacht.springframework.definition.BeanDefinition;
import com.nacht.springframework.definition.BeanReference;
import com.nacht.springframework.definition.PropertyValue;
import com.nacht.springframework.definition.PropertyValues;
import com.nacht.springframework.instantiation.CglibSubclassingInstantiationStrategy;
import com.nacht.springframework.instantiation.InstantiationStrategy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * 容器抽象类
 * @author Nacht
 * Created on 2021/8/30
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory{

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
        try {
            /*通过BeanDefinition创建实例*/
            bean = createBeanInstance(name, beanDefinition, args);
            /*属性填充*/
            applyPropertyValues(name, bean, beanDefinition);
            /*执行BeanPostProcessor的前置和后置处理方法*/
            bean = initializeBean(name, beanDefinition, bean);
            /*如果bean指定了销毁方法, 将其加入到需要执行销毁方法的bean集合中*/
            registerDisposableBeanIfNecessary(name, beanDefinition, bean);
            /*将实例对象添加到单例注册表中*/
            addSingleton(name, bean);
            return bean;
        } catch (Exception e) {
            throw new BeansException("create bean failed", e);
        }
    }

    /**
     * 判断bean是否需要执行销毁方法, 如果需要则将其加入到对应的集合中, 后面统一调用销毁方法
     * 如果是接口方式, 直接添加
     * 如果是配置方式, 并未实现接口, 用DisposableBean接口的适配器封装后添加
     * @param name
     * @param beanDefinition
     * @param bean
     */
    protected void registerDisposableBeanIfNecessary(String name, BeanDefinition beanDefinition, Object bean){
        if(bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())){
            registerDisposableBean(name, new DisposableBeanAdapter(bean, name, beanDefinition));
        }
    }


    /**
     * 封装一个方法来进行bean的创建具体逻辑
     * @param name
     * @param beanDefinition
     * @param args
     * @return
     */
    protected Object createBeanInstance(String name, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        /*根据参数类型找到对应的constructor*/
        /*先尝试根据args匹配对应的constructor, 如果找不到, 就抛出异常*/
        Constructor<?> ctor = null;
        for (Constructor<?> c : beanDefinition.getBeanClass().getDeclaredConstructors()) {
            /*带参的情况 -> args不为null, 构造函数的参数类型数组长度和args长度相等, 实际应该还要判断每一个参数的类型是否相等, 这里不做判断, 交给下一层创建的时候cglib和jdk匹配, 如果不匹配抛异常即可*/
            if (args != null && args.length == c.getParameterTypes().length) {
                ctor = c;
                break;
            }
        }
        /*传入的参数不为空但是没匹配上, 说明没有对应的有参构造器, 抛出异常*/
        if (args != null && ctor == null) {
            throw new BeansException("can not find match constructor in class " + beanDefinition.getBeanClass().toString() + " with parameters " + Arrays.toString(args));
        }
        return instantiationStrategy.instantiate(name, beanDefinition, ctor, args);
    }

    /**
     * 进行bean属性填充(依赖注入)
     * 当前版本暂时没解决循环依赖的问题, 留给后面解决
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    private void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            /*循环进行属性注入*/
            for (PropertyValue propertyNameValue : propertyValues.getPropertyValues()) {
                String propName = propertyNameValue.getName();
                Object propValue = propertyNameValue.getValue();
                /*如果属性值为BeanReference, 在容器中找到对应的bean进行替换*/
                if (propValue instanceof BeanReference) {
                    propValue = getBean(propName);
                }
                Field field = beanDefinition.getBeanClass().getDeclaredField(propName);
                field.setAccessible(true);
                field.set(bean, propValue);
            }
        } catch (Exception e) {
            throw new BeansException("error setting property values " + beanName, e);
        }

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

    /**
     * 执行BeanPostProcessor的前置和后置方法和初始化方法
     * @param name
     * @param beanDefinition
     * @param bean
     * @return
     */
    private Object initializeBean(String name, BeanDefinition beanDefinition, Object bean) throws BeansException{
        try{
            /*执行Aware类型bean的信息注入方法*/
            invokeAwareMethods(name, bean);
            /*执行前置方法*/
            Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, name);
            /*执行初始化方法*/
            invokeInitMethods(name, bean, beanDefinition);
            /*执行后置方法*/
            wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, name);
            return wrappedBean;
        }catch (Exception e){
            throw new BeansException("Initialize bean " + name + " failed", e);
        }
    }

    /**
     * 执行实现Aware接口的bean的Aware信息注入方法
     * @param beanName
     * @param bean
     */
    private void invokeAwareMethods(String beanName, Object bean){
        if(bean instanceof Aware){
            if (bean instanceof BeanNameAware) {
                ((BeanNameAware) bean).setBeanName(beanName);
            }
            if (bean instanceof BeanClassLoaderAware) {
                ClassLoader bcl = getBeanClassLoader();
                if (bcl != null) {
                    ((BeanClassLoaderAware) bean).setBeanClassLoader(bcl);
                }
            }
            if (bean instanceof BeanFactoryAware) {
                ((BeanFactoryAware) bean).setBeanFactory(AbstractAutowireCapableBeanFactory.this);
            }
        }
    }

    /**
     * 执行初始化方法, 暂未实现
     * @param name
     * @param bean
     * @param beanDefinition
     * @throws Exception
     */
    private void invokeInitMethods(String name, Object bean, BeanDefinition beanDefinition) throws Exception{
        /*spring中三种方式优先级是@PostConstruct > 自定义配置 > 接口, 这里实现的两种遵循上面的顺序*/
        /* 1.xml配置初始化方法 */
        String initMethodName = beanDefinition.getInitMethodName();
        if(StrUtil.isNotEmpty(initMethodName)){
            Method initMethod = beanDefinition.getBeanClass().getDeclaredMethod(initMethodName);
            initMethod.invoke(bean);
        }
        /* 2. 接口方式配置初始化方法 */
        if(bean instanceof InitializingBean){
            ((InitializingBean) bean).afterPropertiesSet();
        }
    }

    /**
     * 执行BeanPostProcessor前置方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object bean, String beanName) throws BeansException {
        Object result = bean;
        List<BeanPostProcessor> beanPostProcessors = getBeanPostProcessors();
        for(int i = 0; i < beanPostProcessors.size(); i++){
            BeanPostProcessor beanPostProcessor = beanPostProcessors.get(i);
            result = beanPostProcessor.postProcessBeforeInitialization(result, beanName);
            /*如果一个BeanPostProcessor将bean改为了null, 其他BeanPostProcessor就没机会再对其进行修改了*/
            if(result == null){
                return result;
            }
        }
        return result;
    }

    /**
     * 执行BeanPostProcessor后置方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object bean, String beanName) throws BeansException {
        Object result = bean;
        List<BeanPostProcessor> beanPostProcessors = getBeanPostProcessors();
        for(int i = 0; i < beanPostProcessors.size(); i++){
            BeanPostProcessor beanPostProcessor = beanPostProcessors.get(i);
            result = beanPostProcessor.postProcessAfterInitialization(result, beanName);
            /*如果一个BeanPostProcessor将bean改为了null, 其他BeanPostProcessor就没机会再对其进行修改了*/
            if(result == null){
                return result;
            }
        }
        return result;
    }

}
