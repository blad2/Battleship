import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final double gravity = 9.8;
        double density = scanner.nextDouble();
        double height = scanner.nextDouble();
        System.out.print(gravity * density * height);
    }
}