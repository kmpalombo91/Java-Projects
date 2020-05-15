package mp7;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Triangle {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("john", 20);
        map.put("mary", 18);
        map.put("aaron", 19);
        
        Set<Map.Entry<String, Integer>> entries = map.entrySet(); // key/value of map
        Set<String> keys = map.keySet(); // only keys of map
        Collection<Integer> values = map.values(); // only values of map
        System.out.println(map);
        
        keys.remove("john");
        System.out.println(keys);
        System.out.println(map);
    }
}
