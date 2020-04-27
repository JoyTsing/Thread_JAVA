import java.util.HashMap;

public class HashMapEndlessLoop {
    //jdk1.8以前才有这种问题
    private static HashMap<Integer, String> map = new HashMap<>(2, 1.5f);

    public static void main(String[] args) {
        map.put(5, "C");
        map.put(7, "B");
        map.put(3, "A");
        /**
         * 高并发下的死循环
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                map.put(15, "D");
                System.out.println(map);
            }
        }, "Thread 1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                map.put(1, "E");
                System.out.println(map);
            }
        }, "Thread 2").start();
    }

}
