package com.nacht.springframework.definition;

/**
 * 当属性为一个引用时(此时PropertyValue对象中的value属性为一个BeanReference对象)的抽象, 代表该属性指向容器中的某个bean, 在进行属性注入的时候, 就根据这个BeanReference对象查找到对应的bean, 然后再将bean赋值给对应的属性
 * 为什么要有这个东西? 设想一下, 假设在xml里面配置了一个bean, 有一个属性值, 引用了另外一个bean2, 在读取配置文件的时候肯定不能直接将bean2赋值给对应的属性值, 只能先通过BeanReference记录下bean2的相关信息, 在依赖注入阶段再通过这个BeanReference对象来查找到对应的bean进行注入
 * 这个就类似于类变量中的符号引用, 在前期无法确定具体对象的时候先保存一个唯一能找到这个对象的标识, 后期再替换成具体的对象
 * @author Nacht
 * Created on 2021/9/1
 */
public class BeanReference {

    /**
     * bean引用对应的bean名称
     */
    private final String beanName;

    /**
     * 构造函数
     * @param name
     */
    public BeanReference(String name){
        this.beanName = name;
    }

    /**
     * 获取beanName
     * @return
     */
    public String getBeanName() {
        return beanName;
    }
}
