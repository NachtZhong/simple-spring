package com.nacht.springframework.context;

import com.nacht.springframework.BeansException;
import com.nacht.springframework.container.ConfigurableListableBeanFactory;
import com.nacht.springframework.container.DefaultListableBeanFactory;

/**
 * ApplicationContext抽象类的扩展抽象类
 * @author Nacht
 * Created on 2021/9/5
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    /**
     * 内部组合bean factory
     */
    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        /* 1.创建容器 */
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        /* 2.加载BeanDefinition */
        loadBeanDefinitions(beanFactory);
        /* 3.组合 */
        this.beanFactory = beanFactory;
    }

    /**
     * 创建容器实例
     * @return
     */
    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    /**
     * 加载BeanDefinition, 交给具体子类实现, 选择自己需要的BeanDefinitionReader(bean definition加载器)来进行bean definition的加载和注册
     * @param beanFactory
     */
    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    /**
     * 获取bean factory实例
     * @return
     */
    @Override
    public ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
