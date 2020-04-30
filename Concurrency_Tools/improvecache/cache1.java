package improvecache;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @description：最简单的缓存形式：HashMap
 * 问题：有线程安全与并发安全问题,在computer上加synchronized性能变差
 *      复用性差,耦合度高
 */
public class cache1 {
    private final HashMap<String, Integer> cache = new HashMap<>();

    public synchronized Integer computer(String userId) throws InterruptedException {
        Integer result = cache.get(userId);
        if (result == null) {
            result = doComputer(userId);
            cache.put(userId, result);
        }
        return result;
    }

    private Integer doComputer(String userId) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return Integer.valueOf(userId);
    }

    public static void main(String[] args) throws InterruptedException {
        cache1 cache1 = new cache1();
        System.out.println("begin");
        Integer result=cache1.computer("13");
        System.out.println("第一次计算结果"+result);
        result=cache1.computer("13");
        System.out.println("第二次计算结果"+result);
    }
}
