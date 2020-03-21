package threadcoreknowledge.createthreads;

/**
 * 描述：  用runnable方式创建线程,通常使用runnable，实现了解耦
 *
 */
public class RunnableStyle implements Runnable{

    public static void main(String[] args) {
       Thread thread= new Thread(new RunnableStyle());
       thread.start();
    }

    @Override
    public void run() {
        System.out.println("用Runnable方法实现线程");
    }
}
