// write your answer here 

import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;


class Task {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printMap(wordCount(scanner.nextLine()));

    }

    public static SortedMap<String, Integer> wordCount(String entry) {
        String[] entries = entry.split(" ");
        SortedMap<String, Integer> entryMap = new TreeMap<>();
        for (String key : entries) {
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
        for (Map.Entry<String, Integer> map: sortedMap.entrySet()) {
            System.out.println(map.getKey() + " : " + map.getValue());
        }
    }
}
