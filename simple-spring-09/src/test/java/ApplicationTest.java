import com.nacht.bean.PrototypeBean;
import com.nacht.bean.SingletonBean;
import com.nacht.factory.PrototypeObject;
import com.nacht.factory.SingletonObject;
import com.nacht.springframework.context.ClassPathXmlApplicationContext;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Nacht
 * Created on 2021/8/30
 */
public class ApplicationTest {

    ClassPathXmlApplicationContext applicationContext;

    @Before
    public void initApplicationContext(){
        applicationContext = new ClassPathXmlApplicationContext();
    }

    @Test
    public void testSingletonBean(){
        SingletonBean singletonBean = applicationContext.getBean("singletonBean", SingletonBean.class);
        SingletonBean singletonBean2 = applicationContext.getBean("singletonBean", SingletonBean.class);
        System.out.println(singletonBean == singletonBean2); //true
    }

    @Test
    public void testPrototypeBean(){
        PrototypeBean prototypeBean = applicationContext.getBean("prototypeBean", PrototypeBean.class);
        PrototypeBean prototypeBean2 = applicationContext.getBean("prototypeBean", PrototypeBean.class);
        System.out.println(prototypeBean == prototypeBean2); //false
    }

    @Test
    public void testSingletonObjectFactory(){
        SingletonObject singletonObject = applicationContext.getBean("singletonObject", SingletonObject.class);
        SingletonObject singletonObject2 = applicationContext.getBean("singletonObject", SingletonObject.class);
        System.out.println(singletonObject);
        System.out.println(singletonObject2);
        System.out.println(singletonObject == singletonObject2);
    }

    @Test
    public void testPrototypeObjectFactory(){
        PrototypeObject prototypeObject = applicationContext.getBean("prototypeObject", PrototypeObject.class);
        PrototypeObject prototypeObject2 = applicationContext.getBean("prototypeObject", PrototypeObject.class);
        System.out.println(prototypeObject);
        System.out.println(prototypeObject2);
        System.out.println(prototypeObject == prototypeObject2);
    }
}
