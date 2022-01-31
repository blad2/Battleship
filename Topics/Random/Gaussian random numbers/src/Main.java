import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int n = scanner.nextInt();
        double m = scanner.nextDouble();

        while (true) {
            Random random = new Random(k);
            boolean lessEqualM = true;
            for (int i = 0; i < n; i++) {
                lessEqualM = lessEqualM && random.nextGaussian() <= m;
            }
            if (lessEqualM) {
                break;
            }
            k++;
        }

        System.out.println(k);
    }
}