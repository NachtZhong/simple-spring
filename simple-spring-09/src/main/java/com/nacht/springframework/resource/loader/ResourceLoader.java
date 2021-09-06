package com.nacht.springframework.resource.loader;

import com.nacht.springframework.resource.Resource;

/**
 * 资源加载器抽象接口
 * @author Nacht
 * Created on 2021/9/2
 */
public interface ResourceLoader {

    /**
     * 使用环境变量文件路径时前缀
     */
    String CLASSPATH_URL_PREFIX = "classpath:";

    /**
     * 根据路径获取文件资源对象
     * @param location 文件路径(环境变量路径, 绝对路径或者url)
     * @return
     */
    Resource getResource(String location);

}
