import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

class MapUtils {

    public static SortedMap<String, Integer> wordCount(String[] strings) {
        SortedMap<String, Integer> entryMap = new TreeMap<>();
        for (String key : strings) {
            // if key is on the map
            // increase its value by one
            if (!entryMap.containsKey(key)) {
                entryMap.put(key, 1);
            } else {
                int keyCurrentValue = entryMap.get(key) + 1;
                entryMap.put(key, keyCurrentValue);
            }
        }
        return entryMap;
    }

    public static void printMap(SortedMap<String, Integer> sortedMap) {
        // write your code here
        for (Map.Entry<String, Integer> map: sortedMap.entrySet()) {
            System.out.println(map.getKey() + " : " + map.getValue());
        }
    }

}

/* Do not change code below */
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words = scanner.nextLine().split(" ");
        MapUtils.printMap(MapUtils.wordCount(words));
    }
}