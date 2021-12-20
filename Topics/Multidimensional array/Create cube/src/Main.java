class ArrayOperations {
    public static int[][][] createCube() {
        int[][][] cube = new int[3][3][3];

        //adding the collumns
        for (int i = 0; i < cube.length; i++) {
            //adding the rows
            for (int j = 0; j < cube[i].length; j++) {
                //adding individual array inside a each cell
                for (int k = 0; k < cube[i][j].length; k++) {
                    if (j == 1) {
                        cube[i][j][k] = k + 3;
                    } else if (j == 2) {
                        cube[i][j][k] = k + 6;
                    } else {
                        cube[i][j][k] = k;
                    }
                }
            }
        }
        return cube;
    }
}
