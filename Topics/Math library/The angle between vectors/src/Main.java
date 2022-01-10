import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String[] vector1 = scanner.nextLine().split(" ");
        String[] vector2 = scanner.nextLine().split(" ");
        double scalar1V1 = Double.parseDouble(vector1[0]);
        double scalar2V1 = Double.parseDouble(vector1[1]);
        double scalar1V2 = Double.parseDouble(vector2[0]);
        double scalar2V2 = Double.parseDouble(vector2[1]);
        // calculate the product by multiplying scalar1 v1 to scalar1 to v2 plus scalar2 v1 to scalar2 v2.
        int product = (int) (scalar1V1 * scalar1V2) + (int) (scalar2V1 * scalar2V2);
        // calculate the vector length for each (sqrt of the sum of both element to the power of two).
        double magnitude1 = Math.sqrt(Math.pow(scalar1V1, 2) + Math.pow(scalar2V1, 2));
        double magnitude2 = Math.sqrt(Math.pow(scalar1V2, 2) + Math.pow(scalar2V2, 2));
        double angleBetweenV = product / (magnitude1 * magnitude2);
        System.out.println(Math.toDegrees(Math.acos(angleBetweenV)));
    }
}