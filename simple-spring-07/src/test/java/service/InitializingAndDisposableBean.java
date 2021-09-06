package service;

import com.nacht.springframework.bean.DisposableBean;
import com.nacht.springframework.bean.InitializingBean;

/**
 * @author Nacht
 * Created on 2021/9/6
 */
public class InitializingAndDisposableBean implements InitializingBean, DisposableBean {
    @Override
    public void afterPropertiesSet() {
        System.out.println("InitializingAndDisposableBean ===> afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("InitializingAndDisposableBean ===> destroy");
    }
}
