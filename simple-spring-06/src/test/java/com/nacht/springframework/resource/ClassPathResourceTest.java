package com.nacht.springframework.resource;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Nacht
 * Created on 2021/9/2
 */
public class ClassPathResourceTest extends TestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        System.out.println("hh");
    }

    @Test
    public void testInputStream() throws IOException {
        String path = "123.txt";
        InputStream is = new ClassPathResource(path).getInputStream();
        StringBuilder sb = new StringBuilder();
        int c = is.read();
        while (c != -1){
            sb.append((char) c);
            c = is.read();
        }
        System.out.println(sb);
    }
}