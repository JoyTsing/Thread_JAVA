package threadcoreknowledge.stopthreads;

/**
 * @description： 最佳实现2：在catch子语句中调用Thread.currentThread().isInterrupt()来恢复设置中断状态，
 * 以便于在后续的执行中，依然能够检查到刚才发生了中断然后跳出
 */
public class RightWayStopThreadInProd2 implements Runnable {


    @Override
    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupted!");
                break;
            }
            System.out.println("go");
            reInterrupt();
        }
    }

    private void reInterrupt() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd2());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

}
