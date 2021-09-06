package com.nacht.springframework.context;

import com.nacht.springframework.BeansException;
import com.nacht.springframework.container.ConfigurableBeanFactory;
import com.nacht.springframework.container.ConfigurableListableBeanFactory;
import com.nacht.springframework.context.extension.BeanFactoryPostProcessor;
import com.nacht.springframework.context.extension.BeanPostProcessor;
import com.nacht.springframework.resource.loader.DefaultResourceLoader;

import java.util.Map;

/**
 * 抽象的应用上下文类
 * @author Nacht
 * Created on 2021/9/4
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    /**
     * 刷新容器, 也是spring框架初始化的核心方法
     * @throws BeansException
     */
    @Override
    public void refresh() throws BeansException {

        /* 1. 创建bean factory, 加载bean definition */
        refreshBeanFactory();

        /* 2. 获取bean factory */
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        /* 3. 在bean实例化之前, 执行BeanFactoryPostProcessor*/
        invokeBeanFactoryPostProcessors(beanFactory);

        /*做到这一步的时候我有两个疑问*/
        /* -既然BeanPostProcessor本身也是bean, 为什么初始化的时候不会执行BeanPostProcessor的方法? */
        /* 答案是先通过registerBeanPostProcessors注册所有的BeanPostProcessor, 内部是先通过getBeansOfType获取到所有BeanPostProcessor类型的Bean */
        /* 再一起加入到BeanPostProcessor的集合中 */
        /* 所以这批bean在创建过程中访问BeanPostProcessor的集合得到的都是空集合, 也就不会执行对应的前置后置逻辑了*/
        /* -配置文件中的bean既不配置id也不配置name, 为什么也会有默认的beanName? */
        /* 答案是在BeanDefinitionLoader实现类的中做了判断, 如果bean既没有配置id也没配置name, 就取第一个字母小写后的类名作为bean的名字(autowired同理) */
        /* 4. 提前创建并注册BeanPostProcessor的bean到容器中, 且将这批bean存储在另外一个集合中(其他bean初始化的时候访问该集合来执行前置后置逻辑)*/
        registerBeanPostProcessors(beanFactory);

        /* 5. 提前实例化非lazy-init的单例对象 */
        /* 这里没有判断是否为单例对象, 也没有判断是否为lazy-init, 只是做了简化版, 要做也很简单, 在bean definition类中添加scope和isLazyInit, 实例化的时候判断一下即可 */
        beanFactory.preInstantiateSingletons();
    }

    /**
     * 刷新容器, 其实就是初始化一个BeanFactory, 子类实现
     * @throws BeansException
     */
    protected abstract void refreshBeanFactory() throws BeansException;

    /**
     * 获取BeanFactory实例
     * @return
     */
    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    /**
     * 执行bean factory后置处理器
     * 由于BeanFactoryPostProcessor在整个程序的生命周期一般只会在bean definition全部注册完, bean实例化前执行一次, 不用缓存起来
     * 后面的BeanPostProcessor由于每次创建bean, 初始化前后都会调用, 会单独缓存一份集合, 提高性能
     * @param beanFactory
     */
    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory){
         beanFactory.getBeansOfType(BeanFactoryPostProcessor.class).values().forEach(processor -> processor.postProcessBeanFactory(beanFactory));
    }

    /**
     * 提前注册所有的BeanPostProcessor
     * 要注意这里BeanPostProcessor bean创建的时候是不会受自身或者其他BeanPostProcessor影响的
     * 因为它是先统一全部创建出来, 再将所有的BeanPostProcessor添加到集合中, 在创建的时候取获取集合中的BeanPostProcessor还是空集合, 故processor的创建不会受processor影响
     * @param beanFactory
     */
    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory){
        /*获取所有BeanPostProcessor的实例*/
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        /*将所有的BeanPostProcessor实例添加到BeanPostProcessor集合中*/
        beanPostProcessorMap.values().forEach(beanFactory :: addBeanPostProcessor);
    }

    /**
     * 组合BeanFactory对应的方法即可
     * @param type
     * @param <T>
     * @return
     */
    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return getBeanFactory().getBeansOfType(type);
    }

    /**
     * 组合BeanFactory对应的方法即可
     * @return
     */
    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    /**
     * 组合BeanFactory对应的方法即可
     * @param name
     * @return
     */
    @Override
    public Object getBean(String name) {
        return getBeanFactory().getBean(name);
    }

    /**
     * 组合BeanFactory对应的方法即可
     * @param name
     * @param args
     * @return
     */
    @Override
    public Object getBean(String name, Object... args) {
        return getBeanFactory().getBean(name, args);
    }

    /**
     * 组合BeanFactory对应的方法即可
     * @param name
     * @param type
     * @param <T>
     * @return
     */
    @Override
    public <T> T getBean(String name, Class<T> type) {
        return getBeanFactory().getBean(name, type);
    }

    /**
     * 注册容器销毁方法到虚拟机shutdown hook中
     */
    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this :: close));
    }

    /**
     * 虚拟机关闭时的回调方法, 该方法会被注册到虚拟机的shutdown hook中
     */
    @Override
    public void close() {
        getBeanFactory().destroySingletons();
    }
}
