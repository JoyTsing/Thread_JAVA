package singleton;

/**
 * @description：饿汉式(静态代码块)（可用）
 */
public class singleton2 {
    private final static singleton2 INSTANCE;

    static {
        INSTANCE = new singleton2();
    }

    private singleton2() {

    }

    public static singleton2 getInstance() {
        return INSTANCE;
    }
}
