package processors;

import com.nacht.springframework.BeansException;
import com.nacht.springframework.context.extension.BeanPostProcessor;
import service.UserService;

/**
 * @author Nacht
 * Created on 2021/9/5
 */
public class MyBeanPostProcessor1 implements BeanPostProcessor {

    private String processorName = "MyBeanPostProcessor1";

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof UserService){
            bean = new UserService("fuck");
        }
        System.out.println(String.format("bean[%s], BeanPostProcessor[%s], 前置处理方法", beanName, processorName));
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(String.format("bean[%s], BeanPostProcessor[%s], 后置处理方法", beanName, processorName));
        return bean;
    }
}
