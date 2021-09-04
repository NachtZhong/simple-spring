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

}
