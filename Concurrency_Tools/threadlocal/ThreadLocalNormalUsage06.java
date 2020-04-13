package threadlocal;

import java.security.Provider;

/**
 * @description：避免传参的麻烦
 */
public class ThreadLocalNormalUsage06 {

    public static void main(String[] args) {
        new Service1().process();
    }


}

class Service1 {
    public void process() {
        User user = new User("杰哥");
        UserContextHolder.holder.set(user);
        new Service2().process();
    }
}

class Service2 {
    public void process() {
        User user = UserContextHolder.holder.get();
        System.out.println("Service2: "+user.name);
        new Service3().process();
    }
}

class Service3{
    public void process() {
        User user = UserContextHolder.holder.get();
        System.out.println("Service3: "+user.name);
    }
}

class UserContextHolder{
    public static ThreadLocal<User> holder=new ThreadLocal<>();
}

class User {
    String name;
    public User(String name) {
        this.name = name;
    }
}