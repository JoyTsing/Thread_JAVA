package singleton;

/**
 * @description：懒汉式(线程不安全)
 */
public class singleton3 {
    private static singleton3 instance;

    private singleton3() {

    }

    public static singleton3 getInstance() {
        if (instance == null) {
            instance = new singleton3();
        }
        return instance;
    }


}
