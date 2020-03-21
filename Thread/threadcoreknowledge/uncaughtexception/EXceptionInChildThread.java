package threadcoreknowledge.uncaughtexception;

/**
 * @description：TODO
 */
public class EXceptionInChildThread implements Runnable{

    public static void main(String[] args) {
        new Thread(new EXceptionInChildThread()).start();
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
