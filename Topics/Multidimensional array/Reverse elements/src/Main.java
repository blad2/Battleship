
class ArrayOperations {
    public static void reverseElements(int[][] twoDimArray) {
        for (int i = 0; i < twoDimArray.length; i++) {
            int[] row = new int[twoDimArray[i].length];
            for (int j = row.length - 1; j >= 0; j--) {
                row[j] = twoDimArray[i][row.length - 1 - j];
            }
            twoDimArray[i] = row;
        }
    }
}
