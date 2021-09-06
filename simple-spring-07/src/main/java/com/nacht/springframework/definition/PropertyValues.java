package com.nacht.springframework.definition;

import java.util.ArrayList;
import java.util.List;

/**
 * 代表bean里面所有的属性集合
 * @author Nacht
 * Created on 2021/9/1
 */
public class PropertyValues {

    /**
     * 所有的属性集合
     */
    private List<PropertyValue> propertyValues = new ArrayList<>();

    /**
     * 获取所有的属性
     * @return
     */
    public List<PropertyValue> getPropertyValues() {
        return propertyValues;
    }

    /**
     * 添加属性
     * @param propertyValue
     */
    public void addPropertyValue(PropertyValue propertyValue){
        propertyValues.add(propertyValue);
    }

    /**
     * 根据名字获取对应的属性信息, 找不到就返回null
     * @param name
     * @return
     */
    public PropertyValue getPropertyValue(String name){
        return propertyValues.stream().filter(propertyValue -> name.equals(propertyValue.getName())).findFirst().orElse(null);
    }
}
