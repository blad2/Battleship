import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        Map<Integer, Integer> seedAndInt = new TreeMap<>();
        Random random = new Random();
        for (int i = a; i <= b; i++) {
            random.setSeed(i);
            seedAndInt.put(i, 0);
            for (int j = 0; j < n; j++) {
                int currentRandom = random.nextInt(k);
                if (currentRandom > seedAndInt.get(i)) {
                    seedAndInt.put(i, currentRandom);
                }
            }
        }
        int min = seedAndInt.get(a);
        int seed = 0;
        for (Map.Entry<Integer, Integer> map: seedAndInt.entrySet()) {
            if (map.getValue() < min) {
                min = map.getValue();
                seed = map.getKey();
            }
        }
        System.out.println(seed);
        System.out.println(min);
    }
}
