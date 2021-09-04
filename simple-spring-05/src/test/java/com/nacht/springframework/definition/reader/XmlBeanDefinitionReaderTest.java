package com.nacht.springframework.definition.reader;

import com.nacht.springframework.container.DefaultListableBeanFactory;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Nacht
 * Created on 2021/9/2
 */
public class XmlBeanDefinitionReaderTest extends TestCase {
    @Test
    public void testXmlBeanDefinitionReader(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:applicationContext.xml");
        System.out.println(Arrays.toString(beanFactory.getBeanDefinitionNames()));
    }
}