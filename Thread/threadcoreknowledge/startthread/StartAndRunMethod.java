package threadcoreknowledge.startthread;

/**
 * 对比start和run两种启动线程的方式
 */
public class StartAndRunMethod {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName());
        };
        runnable.run();//由main来跑

        new Thread(runnable).start();//由新线程跑
    }


}
