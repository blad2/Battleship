import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] dimensions = scanner.nextLine().split(" ");
        int[][] matrix =  new int[Integer.parseInt(dimensions[0])][Integer.parseInt(dimensions[1])];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        int i = scanner.nextInt();
        int j = scanner.nextInt();

        for (int r = 0; r < matrix.length; r++) {
            int temp = matrix[r][i];
            matrix[r][i] = matrix[r][j];
            matrix[r][j] = temp;
        }
        for (int[] digitArray : matrix) {
            for (int digit:  digitArray) {
                System.out.print(digit + " ");
            }
            System.out.println();
        }
    }
}