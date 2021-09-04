import com.nacht.springframework.container.BeanFactory;
import com.nacht.springframework.container.DefaultListableBeanFactory;
import com.nacht.springframework.definition.BeanDefinition;
import service.UserService;

/**
 * @author Nacht
 * Created on 2021/8/30
 */
public class Test {
    public static void main(String[] args) {
        /*初始化BeanFactory*/
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        /*注册bean definition*/
        beanFactory.registerBeanDefinition("userService", new BeanDefinition(UserService.class));
        /*获取bean, 测试*/
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.getUserInfo();
        /*测试两次获取的是否为同一个对象(单例)*/
        UserService userService2 = (UserService) beanFactory.getBean("userService");
        System.out.println(userService == userService2);
    }
}
