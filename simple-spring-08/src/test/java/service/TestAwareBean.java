package service;

import com.nacht.springframework.bean.aware.ApplicationContextAware;
import com.nacht.springframework.bean.aware.BeanClassLoaderAware;
import com.nacht.springframework.bean.aware.BeanFactoryAware;
import com.nacht.springframework.bean.aware.BeanNameAware;
import com.nacht.springframework.container.BeanFactory;
import com.nacht.springframework.context.ApplicationContext;

/**
 * @author Nacht
 * Created on 2021/9/6
 */
public class TestAwareBean implements ApplicationContextAware, BeanFactoryAware, BeanNameAware, BeanClassLoaderAware {

    private ApplicationContext applicationContext;

    private ClassLoader classLoader;

    private BeanFactory beanFactory;

    private String beanName;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.classLoader = beanClassLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public String getBeanName() {
        return beanName;
    }
}
