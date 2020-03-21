package threadcoreknowledge.stopthreads.volatiledemo;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @description：陷入阻塞时，volatile无法停止线程 此例中，生产者生产速度快，消费者消费速度慢，出现阻塞队列满的情况
 */
public class WrongWayVolatileFixed {

    class Producer implements Runnable {

        BlockingQueue storage;

        public Producer(BlockingQueue storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            int num = 0;
            try {
                while (num < 10000 && !Thread.currentThread().isInterrupted()) {
                    if (num % 100 == 0) {
                        storage.put(num);
                        System.out.println(num + "是一百的倍数,放到仓库中了");
                    }
                    num++;
                    Thread.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("生产者停止运行");
            }
        }

    }

    class Consumer {
        BlockingQueue storage;

        public Consumer(BlockingQueue storage) {
            this.storage = storage;
        }

        public boolean needMoreNums() {
            if (Math.random() > 0.95) {
                return false;
            } else {
                return true;
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue storage=new ArrayBlockingQueue(10);
        WrongWayVolatileFixed body=new WrongWayVolatileFixed();
        //生产者
        Producer producer= body.new Producer(storage);
        Thread producerThread =  new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);

        Consumer consumer= body.new Consumer(storage);
        while(consumer.needMoreNums()){
            System.out.println(consumer.storage.take()+"被消费了");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多数据了。");

        producerThread.interrupt();

    }

}

