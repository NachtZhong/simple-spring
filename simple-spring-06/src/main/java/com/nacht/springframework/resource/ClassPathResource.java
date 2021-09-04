package com.nacht.springframework.resource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 位于环境变量中的资源, 用户指定时用了classpath:/xxx这种配置时使用
 * @author Nacht
 * Created on 2021/9/2
 */
public class ClassPathResource implements Resource {

    /**
     * 文件的路径, 由于这个资源类是用户指定时用了classpath:/xxx这种配置时使用, 所以这里的path是相对路径
     */
    private final String path;

    /**
     * 使用的类加载器
     */
    private ClassLoader classLoader;

    /**
     * 只传路径时, 使用默认类加载器
     * @param path
     */
    public ClassPathResource(String path) {
        this(path, null);
    }

    /**
     * 构造函数
     * @param path
     * @param classLoader
     */
    public ClassPathResource(String path, ClassLoader classLoader) {
        this.path = path;
        this.classLoader = classLoader == null ? ClassPathResource.class.getClassLoader() : classLoader;
    }


    /**
     * 获取资源的流
     * @return
     * @throws IOException
     */
    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = classLoader.getResourceAsStream(path);
        if(is == null){
            throw new FileNotFoundException(path + " can not be opened because it does not exist");
        }
        return is;
    }
}
