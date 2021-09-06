package service;

/**
 * @author Nacht
 * Created on 2021/8/30
 */
public class UserService {

    private UserDao userDao;

    private String name;

    public UserService(){

    }

    public UserService(String name){
        this.name = name;
    }

    public void getUserInfo(){
        System.out.println("当前用户:" + name);
    }

    public void queryUserInfo(){
        System.out.println(userDao.queryUserInfo());
    }

    public void init(){
        System.out.println("你好啊小老弟");
    }

    public void setName(String name) {
    }

    public void setUserDao(UserDao userDao) {
    }

    public void destroy(){
        System.out.println("再见了小老弟");
    }
}
