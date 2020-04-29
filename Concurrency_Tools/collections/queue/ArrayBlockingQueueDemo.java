package collections.queue;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockingQueueDemo {

    public static void main(String[] args) {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(3);
        Interviewer r1 = new Interviewer(queue);
        Consumer r2=new Consumer(queue);
        new Thread(r1).start();
        new Thread(r2).start();
    }
}

class Interviewer implements Runnable {
    BlockingQueue<String> queue;

    public Interviewer(BlockingQueue queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        System.out.println("*****star*****");
        for (int i = 0; i < 10; i++) {
            String tmp = "Candidate@ " + i;
            try {
                queue.put(tmp);
                System.out.println(tmp + "开始了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            queue.put("stop");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable {
    BlockingQueue<String> queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String msg;
        try {
            while (!(msg = queue.take()).equals("stop"))
                System.out.println(msg + "摸了");
            System.out.println("歇逼了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}
