package collections.predecessor;

import java.util.*;

public class SynList {
    public static void main(String[] args) {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        list.add(5);
        System.out.println(list.get(0));

        Collections.synchronizedMap(new HashMap<>());//线程安全的Map

    }
}
