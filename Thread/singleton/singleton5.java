package singleton;

/**
 * @description：TODO
 */
public class singleton5 {
    private static singleton5 instance;

    private singleton5() {
    }

    public static singleton5 getInstance() {
        if (instance == null) {
            synchronized (singleton5.class){
                instance = new singleton5();
            }
        }
        return instance;
    }

}
