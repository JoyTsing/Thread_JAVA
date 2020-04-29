package flowcontrol.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class cyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("准备完毕");
            }
        });
        for (int i = 0; i < 5; i++) {
            new Thread(new Task(i,cyclicBarrier)).start();
        }
    }

    static class Task implements Runnable{

        private int id;
        private CyclicBarrier cyclicBarrier;

        public Task(int id, CyclicBarrier cyclicBarrier) {
            this.id = id;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程"+id+"现在准备");
            try {
                Thread.sleep((long) (Math.random()*10000));
                System.out.println("线程"+id+"等待其他线程");
                cyclicBarrier.await();
                System.out.println("线程"+id+"开始了");

            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
