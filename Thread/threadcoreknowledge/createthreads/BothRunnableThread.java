package threadcoreknowledge.createthreads;

/**
 * 描述：
 * 同时使用runnable和thread两种方法实现线程
 * 在匿名内部类中实现runnable对象 然后再覆盖run方法
 *
 * 因此我们可以准确的说，创建线程只有一种方法就是构建Thread类，而实现线程的执行单元有两种方式
 * 从oracle的Thread的源码就可以得知了
 */

public class BothRunnableThread {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我来自Runnable");
            }
        }) {
            @Override
            public void run() {
                System.out.println("我来自Thread");
            }
        }.start();
    }
}
