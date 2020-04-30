package improvecache;

import improvecache.computable.Computable;
import improvecache.computable.ExpensiveFunction;

import java.util.HashMap;
import java.util.Map;

/**
 * @description： HashMap不安全实际上是并发读写造成的，减小锁的力度,对put上锁，使不能够同时put操作
 * 但是这不意味着线程安全，还要考虑到同时有读有写
 * 因此没必要自己升级HashMap，不如使用官方的ConcurrentHashMap
 */
public class cache4<A, V> implements Computable<A, V> {

    private final Map<A, V> cache = new HashMap();
    private final Computable<A, V> c;

    public cache4(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws Exception {
        System.out.println("进入缓存机制");
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            synchronized (this) {
                cache.put(arg, result);
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        cache4<String, Integer> computer = new cache4<>(new ExpensiveFunction());
        Integer result = computer.compute("666");
        System.out.println("第一次计算结果" + result);
        result = computer.compute("666");
        System.out.println("第二次计算结果" + result);
    }
}
