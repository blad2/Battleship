import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        double sqrtEquation = Math.pow(b, 2) - 4 * a * c;
        double root1 = (-b - Math.sqrt(sqrtEquation)) / (2 * a);
        double root2 = (-b + Math.sqrt(sqrtEquation)) / (2 * a);
        System.out.print(Math.min(root1, root2) + " ");
        System.out.print(Math.max(root1, root2));
    }
}