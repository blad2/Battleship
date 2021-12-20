import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = Integer.parseInt(input.nextLine());
        int[][] matrix = new int[n][n];
        boolean result = true;
        for (int i = 0; i < n; i++) {
            String[] temp = input.nextLine().split(" ");
            for (int j = 0; j < temp.length; j++) {
                matrix[i][j] = Integer.parseInt(temp[j]);
                //verify if column has the same as row
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    result = false;
                    break;
                }
            }
        }
        System.out.printf("%s", result ? "YES" : "NO");
    }
}
