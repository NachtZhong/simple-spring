import com.nacht.springframework.BeanDefinition;
import com.nacht.springframework.BeanFactory;
import service.UserService;

/**
 * @author Nacht
 * Created on 2021/8/30
 */
public class Test {
    public static void main(String[] args) {
        /*初始化bean factory*/
        BeanFactory beanFactory = new BeanFactory();
        /*将bean注册到容器中*/
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        /*获取bean进行测试*/
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.getUserInfo();
    }
}
