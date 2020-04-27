package collections.predecessor;

import java.util.HashMap;

public class MapDemo {
    public static void main(String[] args) {
        HashMap<String,Integer> map=new HashMap<>();
        System.out.println(map.isEmpty());
        map.put("111",1);
        map.put("222",2);
        System.out.println(map.keySet());
        System.out.println(map.get("111"));
        System.out.println(map.size());
        System.out.println(map.containsKey("111"));
        map.remove("111");
        System.out.println(map.containsKey("111"));
    }
}
