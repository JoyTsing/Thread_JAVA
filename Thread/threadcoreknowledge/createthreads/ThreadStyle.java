package threadcoreknowledge.createthreads;

/**
 * 描述： 用Thread方式实现线程
 */
public class ThreadStyle extends Thread{

    public static void main(String[] args) {
        Thread thread=new ThreadStyle();
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("用Thread类实现线程");
    }


}
