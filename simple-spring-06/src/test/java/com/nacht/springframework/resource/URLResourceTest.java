package com.nacht.springframework.resource;

import junit.framework.TestCase;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Nacht
 * Created on 2021/9/2
 */
public class URLResourceTest extends TestCase {
    public void testInputStream() throws IOException {
        String path = "/Users/nacht/Documents/IDEA/ForBMW/nacht-spring-05/src/main/java/com/nacht/springframework/resource/Resource.java";
        InputStream is = new URLResource(path).getInputStream();
        StringBuilder sb = new StringBuilder();
        int c = is.read();
        while (c != -1){
            sb.append((char) c);
            c = is.read();
        }
        System.out.println(sb);
    }
}