package com.nacht.springframework.definition.reader;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.nacht.springframework.BeansException;
import com.nacht.springframework.definition.*;
import com.nacht.springframework.resource.Resource;
import com.nacht.springframework.resource.loader.DefaultResourceLoader;
import com.nacht.springframework.resource.loader.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

/**
 * xml bean definition加载器
 * @author Nacht
 * Created on 2021/9/2
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader{

    /**
     * 定义一些xml tag的固定值
     */
    private final String XML_TAG_BEANS = "beans";
    private final String XML_TAG_BEAN = "bean";
    private final String XML_TAG_PROPERTY = "property";


    /**
     * bean definition注册表构造器
     * @param registry
     */
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    /**
     * 全参构造器
     * @param registry
     * @param resourceLoader
     */
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    /**
     * 加载单个资源定义的bean definition
     * @param resource
     */
    @Override
    public void loadBeanDefinitions(Resource resource) {
        try {
            InputStream inputStream = resource.getInputStream();
            doLoadBeanDefinition(inputStream);
        } catch(Exception e) {
            throw new BeansException("error loading bean definition", e);
        }
    }


    /**
     * 加载多个资源定义的bean definition
     * @param resources
     */
    @Override
    public void loadBeanDefinitions(Resource... resources) {
        for(Resource resource : resources){
            loadBeanDefinitions(resource);
        }
    }

    /**
     * 从路径文件加载bean definition
     * @param location
     */
    @Override
    public void loadBeanDefinitions(String location) {
        Resource resource = getResourceLoader().getResource(location);
        loadBeanDefinitions(resource);
    }

    /**
     * 从多个路径文件加载bean definition
     * @param locations
     */
    @Override
    public void loadBeanDefinitions(String... locations) {
        for(String location : locations){
            loadBeanDefinitions(location);
        }
    }

    /**
     * 1. 从资源输入流中加载bean definition,
     * 2. 将bean definition注册到注册表中
     * 这里只做了一个简单的xml解析, 默认最外层就是beans, 里面是bean, 再里面是property, 不做太复杂, 明白流程即可
     * @param inputStream
     */
    private void doLoadBeanDefinition(InputStream inputStream) throws ClassNotFoundException {
        /*xml解析*/
        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();
        /*如果最外层不是用beans标签包裹, 不处理*/
        if(!XML_TAG_BEANS.equals(root.getNodeName())){
            return;
        }
        NodeList childNodes = root.getChildNodes();

        /*遍历所有的bean节点解析*/
        for(int i = 0 ; i < childNodes.getLength(); i++){
            /*如果不是一个xml元素跳过*/
            if(!(childNodes.item(i) instanceof Element)){
                continue;
            }
            Element childNode = (Element) childNodes.item(i);
            /*如果不是bean标签跳过*/
            if(!XML_TAG_BEAN.equals(childNode.getNodeName())){
                continue;
            }
            /*解析bean节点*/
            String id = childNode.getAttribute("id");
            String name = childNode.getAttribute("name");
            String className = childNode.getAttribute("class");
            String initMethodName = childNode.getAttribute("init-method");
            String destroyMethodName = childNode.getAttribute("destroy-method");
            /*获取bean的class对象*/
            Class<?> clazz = Class.forName(className);
            /*有id的情况下优先使用id作为bean的唯一标示, 这种情况name可以重复, 但是如果只有name的时候, name必须保证全局唯一*/
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            /*如果没有配置id和name, 取class首字母小写作为beanName*/
            beanName = StrUtil.isEmpty(beanName) ? StrUtil.lowerFirst(clazz.getSimpleName()) : beanName;
            /*如果beanName重复, 抛出异常*/
            if (getRegistry().containsBeanDefinition(beanName)){
                throw new BeansException("duplicate beanName: " + beanName + ", please check your config.");
            }
            /*获取bean的属性*/
            PropertyValues propertyValues = fetchPropertyValuesFromElement(childNode);
            BeanDefinition beanDefinition = new BeanDefinition(clazz, propertyValues);
            beanDefinition.setInitMethodName(initMethodName);
            beanDefinition.setDestroyMethodName(destroyMethodName);
            /*注册bean definition到注册表中*/
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }

    /**
     * 从bean的xml element解析所有的属性并封装到PropertyValues中
     * @param beanElement
     * @return
     */
    private PropertyValues fetchPropertyValuesFromElement(Element beanElement){
        PropertyValues propertyValues = new PropertyValues();
        NodeList childNodes = beanElement.getChildNodes();
        for(int i = 0; i < childNodes.getLength(); i++){
            /*如果不是一个xml元素跳过*/
            if(!(childNodes.item(i) instanceof Element)){
                continue;
            }
            Element childNode = (Element) childNodes.item(i);
            /*如果不是property标签跳过*/
            if(!XML_TAG_PROPERTY.equals(childNode.getNodeName())){
                continue;
            }
            /*解析Property节点*/
            String propertyName = childNode.getAttribute("name");
            String propertyValue = childNode.getAttribute("value");
            String propertyRef = childNode.getAttribute("ref");
            /*如果有ref, 将value封装为一个BeanReference, 进行依赖注入的时候再替换为具体的bean*/
            Object value = StrUtil.isNotEmpty(propertyRef) ? new BeanReference(propertyRef) : propertyValue;
            PropertyValue propertyNameValue = new PropertyValue(propertyName, value);
            propertyValues.addPropertyValue(propertyNameValue);
        }
        return propertyValues;
    }
}
