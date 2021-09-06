import com.nacht.springframework.container.BeanFactory;
import com.nacht.springframework.container.DefaultListableBeanFactory;
import com.nacht.springframework.context.ApplicationContext;
import com.nacht.springframework.context.ClassPathXmlApplicationContext;
import com.nacht.springframework.definition.BeanDefinition;
import com.nacht.springframework.definition.BeanReference;
import com.nacht.springframework.definition.PropertyValue;
import com.nacht.springframework.definition.PropertyValues;
import com.nacht.springframework.definition.reader.BeanDefinitionReader;
import com.nacht.springframework.definition.reader.XmlBeanDefinitionReader;
import com.nacht.springframework.instantiation.SimpleInstantiationStrategy;
import service.UserDao;
import service.UserService;

import java.util.Arrays;

/**
 * @author Nacht
 * Created on 2021/8/30
 */
public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        applicationContext.registerShutdownHook();
    }
}
