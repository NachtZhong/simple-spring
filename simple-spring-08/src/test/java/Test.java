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
import service.TestAwareBean;
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
        TestAwareBean bean = applicationContext.getBean("haha", TestAwareBean.class);
        System.out.println("通过bean的applicationContext属性获取UserService实例...");
        bean.getApplicationContext().getBean("userService", UserService.class).getUserInfo();
        System.out.println("通过bean的beanFactory属性获取UserService实例...");
        bean.getBeanFactory().getBean("userService", UserService.class).getUserInfo();
        System.out.println("获取bean的BeanClassLoader属性...");
        System.out.println(bean.getClassLoader());
        System.out.println("获取bean的beanName属性...");
        System.out.println(bean.getBeanName());

    }
}
