package singleton;

/**
 * @description：静态内部类
 */
public class singleton7 {

    private singleton7(){
    }

    private static class singletonInstance{
        private static final singleton7 INSTANCE =new singleton7();
    }


    public static singleton7 getInstance() {
        return singletonInstance.INSTANCE;
    }

}
