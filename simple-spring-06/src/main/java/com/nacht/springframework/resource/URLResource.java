package com.nacht.springframework.resource;

import cn.hutool.core.util.URLUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 网络文件资源类
 * @author Nacht
 * Created on 2021/9/2
 */
public class URLResource implements Resource{

    private final URL url;

    public URLResource(String url){
        this.url = URLUtil.url(url);
    }


    /**
     * 获取资源文件输入流
     * @return
     * @throws IOException
     */
    @Override
    public InputStream getInputStream() throws IOException {
        return url.openStream();
    }
}
