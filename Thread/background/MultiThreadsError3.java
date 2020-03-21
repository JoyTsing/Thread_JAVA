package background;

import java.util.HashMap;
import java.util.Map;

/**
 * @description：TODO
 */
public class MultiThreadsError3 {
    private Map<String ,String> states;

    public MultiThreadsError3(){
        states=new HashMap<>();
        states.put("1","周一");
        states.put("2","周二");
        states.put("3","周三");
        states.put("4","周四");
    }

    public static void main(String[] args) {
        MultiThreadsError3 multiThreadsError3 = new MultiThreadsError3();
        Map<String, String> states = multiThreadsError3.getStatesImproved();
        System.out.println(multiThreadsError3.getStatesImproved().get("1"));
        states.remove("1");
        System.out.println(multiThreadsError3.getStatesImproved().get("1"));
    }

    public Map<String ,String> getStatesImproved(){
        return new HashMap<>(states);
    }

    public Map<String ,String> getStates(){
        return states;
    }


}
