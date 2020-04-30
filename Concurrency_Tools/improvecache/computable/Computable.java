package improvecache.computable;

/**
 * @description：有一个计算函数computer，用来代表耗时计算 每个计算器都要实现这个接口，来实现无侵入缓存功能
 */
public interface Computable<A, V> {
    public V compute(A arg) throws Exception;
}
