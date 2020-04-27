package collections.concurrenthashmap;


import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class concurrentHashMapDemo {
    /**
     * 文档中的解释
     * The main disadvantage of per-bin locks is that other update
     * operations on other nodes in a bin list protected by the same
     * lock can stall, for example when user equals() or mapping
     * functions take a long time.  However, statistically, under
     * random hash codes, this is not a common problem.  Ideally, the
     * frequency of nodes in bins follows a Poisson distribution
     * (http://en.wikipedia.org/wiki/Poisson_distribution) with a
            * parameter of about 0.5 on average, given the resizing threshold
     * of 0.75, although with a large variance because of resizing
     * granularity. Ignoring variance, the expected occurrences of
     * list size k are (exp(-0.5) * pow(0.5, k) / factorial(k)). The
     * first values are:
            *
            * 0:    0.60653066
            * 1:    0.30326533
            * 2:    0.07581633
            * 3:    0.01263606
            * 4:    0.00157952
            * 5:    0.00015795
            * 6:    0.00001316
            * 7:    0.00000094
            * 8:    0.00000006
            * more: less than 1 in ten million
     **/
    ConcurrentHashMap<Integer,String>map=new ConcurrentHashMap<>();
}
