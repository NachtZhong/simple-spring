package service;

/**
 * @author Nacht
 * Created on 2021/8/30
 */
public class UserService {

    private String name;

    public UserService(){

    }

    public UserService(String name){
        this.name = name;
    }

    public void getUserInfo(){
        System.out.println("当前用户:" + name);
    }

}
