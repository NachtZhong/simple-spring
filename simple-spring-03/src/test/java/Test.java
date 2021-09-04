import com.nacht.springframework.container.BeanFactory;
import com.nacht.springframework.container.DefaultListableBeanFactory;
import com.nacht.springframework.definition.BeanDefinition;
import com.nacht.springframework.instantiation.SimpleInstantiationStrategy;
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
        /*测试使用jdk策略生成bean, 注释即默认使用cglib*/
        beanFactory.setInstantiationStrategy(new SimpleInstantiationStrategy());
        /*获取bean, 测试*/
        UserService userService = (UserService) beanFactory.getBean("userService", 123);
        userService.getUserInfo();
    }
}
