package com.nacht.springframework.bean.aware;

import com.nacht.springframework.context.ApplicationContext;

/**
 * 能感知到自身所属应用上下文的抽象接口
 * @author Nacht
 * Created on 2021/9/6
 */
public interface ApplicationContextAware extends Aware{

    /**
     * 注入ApplicationContext信息
     * @param applicationContext
     */
    void setApplicationContext(ApplicationContext applicationContext);

}
