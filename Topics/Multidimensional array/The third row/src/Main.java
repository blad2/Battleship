class ArrayOperations {
    public static void printTheThirdRow(int[][] twoDimArray) {
        for (int c = 0; c < 3; c++) {
            if (c < 2) {
                System.out.print(" ");
            } else {
                for (int row : twoDimArray[c]) {
                    System.out.print(row + " ");
                }
            }
            System.out.println("");
        }
    }
}