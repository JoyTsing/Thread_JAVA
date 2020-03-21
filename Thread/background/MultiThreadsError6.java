package background;

import java.util.HashMap;
import java.util.Map;

/**
 * @description：TODO
 */
public class MultiThreadsError6 {
    private Map<String ,String> states;

    public MultiThreadsError6(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                states=new HashMap<>();
                states.put("1","周一");
                states.put("2","周二");
                states.put("3","周三");
                states.put("4","周四");
            }
        }).start();

    }

    public static void main(String[] args) throws InterruptedException {
        MultiThreadsError6 multiThreadsError6 = new MultiThreadsError6();
        Thread.sleep(1000);
        Map<String, String> states = multiThreadsError6.getStates();
        System.out.println(states.get("1"));

    }

    public Map<String ,String> getStates(){
        return states;
    }


}
