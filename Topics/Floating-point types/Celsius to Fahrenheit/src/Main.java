import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final double ratio = 1.8;
        final double x = 32;
        double c = scanner.nextDouble();
        System.out.print(c * ratio + x);
    }
}
