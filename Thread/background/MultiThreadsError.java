package background;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description：第一种 运行结果出错
 * 演示技术不准确，找出具体出错的位置
 */
public class MultiThreadsError implements Runnable {

    int index = 0;
    static AtomicInteger realIndex = new AtomicInteger();
    static AtomicInteger wrong = new AtomicInteger();
    final boolean[] marked = new boolean[100000];
    static MultiThreadsError instance = new MultiThreadsError();
    static volatile CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
    static volatile CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("表面上" + instance.index);
        System.out.println("真正的" + realIndex.get());
        System.out.println("错误的" + wrong.get());
    }


    @Override
    public void run() {
        marked[0]=true;//1错误的察觉修正
        for (int i = 0; i < 10000; i++) {
            try {
                cyclicBarrier2.reset();
                cyclicBarrier1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            index++;

            try {
                cyclicBarrier1.reset();
                cyclicBarrier2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            realIndex.incrementAndGet();
            synchronized (instance) {
                if (marked[index] && marked[index - 1]) {
                    System.out.println("冲突位置" + index);
                    wrong.incrementAndGet();
                }
                marked[index] = true;
            }

        }

    }

}
