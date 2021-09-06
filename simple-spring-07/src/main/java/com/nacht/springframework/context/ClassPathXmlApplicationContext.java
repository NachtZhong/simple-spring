package com.nacht.springframework.context;

/**
 * @author Nacht
 * Created on 2021/9/5
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext{

    /**
     * 配置的资源路径
     */
    private String[] configLocations;

    /**
     * 默认配置文件
     */
    private static final String DEFAULT_CONFIG_LOCATION = "classpath:applicationContext.xml";

    /**
     * 默认构造器, 默认使用class path下的applicationContext.xml
     */
    public ClassPathXmlApplicationContext(){
        this(DEFAULT_CONFIG_LOCATION);
    }

    /**
     * 根据指定路径xml配置文件启动容器
     * @param locations
     */
    public ClassPathXmlApplicationContext(String locations){
        this(new String[]{locations});
    }

    /**
     * 根据指定路径xml配置文件启动容器
     * @param locations
     */
    public ClassPathXmlApplicationContext(String[] locations){
        this.configLocations = locations;
        /*一切在此启动!*/
        refresh();
    }


    @Override
    protected String[] getConfigLocations() {
        return this.configLocations;
    }
}
