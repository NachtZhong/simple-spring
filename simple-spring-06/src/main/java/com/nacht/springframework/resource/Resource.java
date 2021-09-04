package com.nacht.springframework.resource;

import java.io.IOException;
import java.io.InputStream;

/**
 * 资源对象的抽象
 * @author Nacht
 * Created on 2021/9/2
 */
public interface Resource {

    /**
     * 获取资源对象的流
     * @return
     */
    InputStream getInputStream() throws IOException;

}
