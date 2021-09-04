package com.nacht.springframework.definition;


/**
 * 代表bean里面一个属性(其实就是一个key-value)
 * @author Nacht
 * Created on 2021/9/1
 */
public class PropertyValue {

    /**
     * 属性名
     */
    private final String name;

    /**
     * 属性值
     */
    private final Object value;

    public PropertyValue(String name, Object value){
        this.name = name;
        this.value = value;
    }

    /**
     * 获取属性名
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 获取属性值
     * @return
     */
    public Object getValue() {
        return value;
    }
}
