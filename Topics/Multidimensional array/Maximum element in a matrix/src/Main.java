import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] size = scanner.nextLine().split(" ");
        int[][] elements = new int[Integer.parseInt(size[0])][Integer.parseInt(size[1])];
        int[] result = new int[2];
        int maxElem = Integer.MIN_VALUE;
        for (int i = 0; i < Integer.parseInt(size[0]); i++) {
            for (int j = 0; j < Integer.parseInt(size[1]); j++) {
                elements[i][j] = scanner.nextInt();
                if (elements[i][j] > maxElem) {
                    maxElem = elements[i][j];
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        System.out.println(result[0] + " " + result[1]);
    }
}
