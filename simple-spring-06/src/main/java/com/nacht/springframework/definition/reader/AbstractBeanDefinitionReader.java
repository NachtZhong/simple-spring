package com.nacht.springframework.definition.reader;

import com.nacht.springframework.definition.BeanDefinitionRegistry;
import com.nacht.springframework.resource.loader.DefaultResourceLoader;
import com.nacht.springframework.resource.loader.ResourceLoader;

/**
 * bean definition加载器抽象类
 * @author Nacht
 * Created on 2021/9/2
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{

    /**
     * bean definition注册表对象
     */
    private final BeanDefinitionRegistry registry;

    /**
     * 资源加载器
     */
    private ResourceLoader resourceLoader;

    /**
     * 只传注册表时, 创建默认的资源加载器(容器本身就是一个BeanDefinition的注册表, 这里直接调用即可, 无需创建)
     * @param registry
     */
    AbstractBeanDefinitionReader(BeanDefinitionRegistry registry){
        this(registry, new DefaultResourceLoader());
    }

    /**
     * 全参构造
     * @param registry
     * @param resourceLoader
     */
    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, DefaultResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader == null ? new DefaultResourceLoader() : resourceLoader;
    }

    /**
     * 获取注册表对象
     * @return
     */
    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    /**
     * 获取资源加载器对象
     * @return
     */
    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
