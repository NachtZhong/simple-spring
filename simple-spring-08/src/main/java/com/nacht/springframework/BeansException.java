package com.nacht.springframework;

/**
 * Bean创建/获取过程中的异常
 * @author Nacht
 * Created on 2021/8/30
 */
public class BeansException extends RuntimeException{

    public BeansException(String msg){
        super(msg);
    }

    public BeansException(String msg, Throwable t){
        super(msg, t);
    }

}
