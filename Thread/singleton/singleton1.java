package singleton;

/**
 * @description：饿汉式(静态常量)（可用）
 */
public class singleton1 {
    private static final singleton1 INSTANCE=new singleton1();

    private singleton1(){

    }

    public static singleton1 getInstance(){
        return INSTANCE;
    }
}
