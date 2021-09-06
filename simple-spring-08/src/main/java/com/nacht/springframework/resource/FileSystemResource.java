package com.nacht.springframework.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件类型资源对象, 用户指定配置文件路径使用绝对路径时使用
 * @author Nacht
 * Created on 2021/9/2
 */
public class FileSystemResource implements Resource{

    /**
     * 文件
     */
    private final File file;

    /**
     * 文件路径
     */
    private final String path;

    /**
     * 通过文件构造
     * @param file
     */
    public FileSystemResource(File file){
        this.file = file;
        this.path = file.getPath();
    }

    /**
     * 通过文件路径构造
     * @param path
     */
    public FileSystemResource(String path){
        this.file = new File(path);
        this.path = path;
    }


    /**
     * 获取文件资源输入流
     * @return
     * @throws IOException
     */
    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(file);
    }

}
