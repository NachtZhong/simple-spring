package com.nacht.springframework.bean;

/**
 * 需要自定义destroy方法的bean的抽象接口
 * @author Nacht
 * Created on 2021/9/6
 */
public interface DisposableBean {

    /**
     * 销毁方法, bean被销毁的时候被调用
     */
    void destroy() throws Exception;

}
