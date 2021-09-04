import com.nacht.springframework.container.BeanFactory;
import com.nacht.springframework.container.DefaultListableBeanFactory;
import com.nacht.springframework.definition.BeanDefinition;
import com.nacht.springframework.definition.BeanReference;
import com.nacht.springframework.definition.PropertyValue;
import com.nacht.springframework.definition.PropertyValues;
import com.nacht.springframework.instantiation.SimpleInstantiationStrategy;
import service.UserDao;
import service.UserService;

/**
 * @author Nacht
 * Created on 2021/8/30
 */
public class Test {
    public static void main(String[] args) {
        /*初始化BeanFactory*/
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        /*UserDao的BeanDefinition*/
        BeanDefinition userDaoDefinition = new BeanDefinition(UserDao.class);
        /*UserService的BeanDefinition, 在UserService的PropertyValues中指定userDao的引用(BeanReference)*/
        PropertyValue nameProp = new PropertyValue("name", "李大锤");
        PropertyValue userDaoProp = new PropertyValue("userDao", new BeanReference("userDap"));
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(nameProp);
        propertyValues.addPropertyValue(userDaoProp);
        BeanDefinition userServiceDefinition = new BeanDefinition(UserService.class, propertyValues);
        /*注册userService和userDao的bean definition*/
        beanFactory.registerBeanDefinition("userDao", userDaoDefinition);
        beanFactory.registerBeanDefinition("userService", userServiceDefinition);
        /*获取bean, 测试*/
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.getUserInfo();
        userService.queryUserInfo();
    }
}
