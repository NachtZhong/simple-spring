package com.nacht.springframework.definition.reader;

import com.nacht.springframework.definition.BeanDefinitionRegistry;
import com.nacht.springframework.resource.Resource;
import com.nacht.springframework.resource.loader.ResourceLoader;

/**
 * BeanDefinition加载器抽象接口
 * @author Nacht
 * Created on 2021/9/2
 */
public interface BeanDefinitionReader {

    /**
     * 获取bean definition注册表(加载器加载完必须注册到注册表中, 所以必定会组合注册表在实现类中, 这波就是预判)
     * @return
     */
    BeanDefinitionRegistry getRegistry();

    /**
     * 获取资源加载器
     * @return
     */
    ResourceLoader getResourceLoader();


    /**
     * 加载某个资源中的bean definition
     * @param resource
     */
    void loadBeanDefinitions(Resource resource);

    /**
     * 加载多个资源中的bean definition
     * @param resources
     */
    void loadBeanDefinitions(Resource... resources);

    /**
     * 加载某个路径文件中配置的bean definition
     * @param location
     */
    void loadBeanDefinitions(String location);



}
