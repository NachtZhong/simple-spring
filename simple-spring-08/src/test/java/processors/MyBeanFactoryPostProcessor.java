package processors;

import com.nacht.springframework.BeansException;
import com.nacht.springframework.container.ConfigurableListableBeanFactory;
import com.nacht.springframework.context.extension.BeanFactoryPostProcessor;
import com.nacht.springframework.definition.PropertyValue;
import com.nacht.springframework.definition.PropertyValues;

/**
 * @author Nacht
 * Created on 2021/9/5
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanFactoryPostProcessor执行");
    }
}
