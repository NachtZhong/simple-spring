package com.nacht.springframework.context;

import com.nacht.springframework.container.DefaultListableBeanFactory;
import com.nacht.springframework.definition.reader.BeanDefinitionReader;
import com.nacht.springframework.definition.reader.XmlBeanDefinitionReader;
import com.nacht.springframework.resource.loader.DefaultResourceLoader;
import com.nacht.springframework.resource.loader.ResourceLoader;

/**
 * ApplicationContext xml扩展类
 * @author Nacht
 * Created on 2021/9/5
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{

    /**
     * 获取配置的资源
     * @return
     */
    abstract protected String[] getConfigLocations();

    /**
     * 加载BeanDefinition
     * @param beanFactory
     */
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = this.getConfigLocations();
        if(configLocations != null){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

}
