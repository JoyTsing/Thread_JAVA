package threadcoreknowledge.stopthreads;

/**
 * @description：TODO
 */
public class StopThread implements Runnable{

    public static void main(String[] args) {
        Thread thread=new Thread(new StopThread());
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.stop();
    }


    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("连队"+i+"开始领取");
            for (int j = 0; j < 10; j++) {
                System.out.println(j);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("连队"+i+"完毕");
        }
    }
}
