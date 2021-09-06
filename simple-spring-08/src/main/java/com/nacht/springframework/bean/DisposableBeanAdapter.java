package com.nacht.springframework.bean;

import cn.hutool.core.util.StrUtil;
import com.nacht.springframework.BeansException;
import com.nacht.springframework.definition.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @author Nacht
 * Created on 2021/9/6
 */
public class DisposableBeanAdapter implements DisposableBean{

    /**
     * bean实例
     */
    private final Object bean;

    /**
     * bean名称
     */
    private final String beanName;

    /**
     * 要执行的销毁方法名
     */
    private final String destroyMethodName;

    /**
     * 构造方法
     * @param bean
     * @param beanName
     * @param beanDefinition
     */
    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition){
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    /**
     * 调用bean指定的销毁方法
     */
    @Override
    public void destroy() throws Exception{
        /*如果bean已经实现了DisposableBean接口, 直接调用destroy方法即可*/
        if(bean instanceof DisposableBean){
            ((DisposableBean) bean).destroy();
        }
        /*如果bean未实现DisposableBean接口, 获取销毁方法执行, 注意这里加一个判断, 避免已经实现了DisposableBean接口, 又在配置里面指定了同一个destroy方法造成destroy方法执行两次*/
        if(StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(destroyMethodName))){
            Method destroyMethod = bean.getClass().getDeclaredMethod(destroyMethodName);
            destroyMethod.invoke(bean);
        }
    }
}
