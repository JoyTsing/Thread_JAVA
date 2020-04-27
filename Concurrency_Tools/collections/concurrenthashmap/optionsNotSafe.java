package collections.concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @description：组合操作不能保证线程安全 只能保证同时put或者get没有问题，在get完后的操作是不能保证的
 */
public class optionsNotSafe implements Runnable {

    private static ConcurrentHashMap<String, Integer> scores = new ConcurrentHashMap<String, Integer>();

    public static void main(String[] args) throws InterruptedException {
        scores.put("xxx", 0);
        Thread t1 = new Thread(new optionsNotSafe());
        Thread t2 = new Thread(new optionsNotSafe());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(scores);
    }

    @Override
    public void run() {
        boolean b=false;
        for (int i = 0; i < 1000; i++) {
            do {
                Integer score = scores.get("xxx");
                Integer newScore = score + 1;
                //do在这里的时候是不正确的，因为存放失败就说明两个冲突
                //不应该继续盲目的重复替换原先的旧值，而是应该重新开始
                b = scores.replace("xxx", score, newScore);
            } while (!b);

        }
    }
}