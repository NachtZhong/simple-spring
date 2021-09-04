package com.nacht.springframework.resource.loader;

import cn.hutool.core.util.URLUtil;
import com.nacht.springframework.resource.ClassPathResource;
import com.nacht.springframework.resource.FileSystemResource;
import com.nacht.springframework.resource.Resource;
import com.nacht.springframework.resource.URLResource;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 资源加载器默认实现类
 * @author Nacht
 * Created on 2021/9/2
 */
public class DefaultResourceLoader implements ResourceLoader{

    /**
     * 根据路径获取文件资源
     * @param location 文件路径(环境变量路径, 绝对路径或者url)
     * @return
     */
    @Override
    public Resource getResource(String location) {
        /*根据文件路径自动加载对应的资源*/
        if(location.startsWith(CLASSPATH_URL_PREFIX)){
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        }
        try {
            return new URLResource(location);
        } catch (Exception e) {
            return new FileSystemResource(location);
        }
    }
}
