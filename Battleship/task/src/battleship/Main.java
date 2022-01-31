package battleship;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    enum Ships {
        AIRCRAFT_CARRIER("Aircraft Carrier", 5),
        BATTLESHIP("Battleship", 4),
        SUBMARINE("Submarine", 3),
        CRUISER("Cruiser", 3),
        DESTROYER("Destroyer", 2);

        final String name;
        final int cells;

        Ships(String name, int cells) {
            this.name = name;
            this.cells = cells;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char[][] field = new char[10][10];
        boolean sentError = false;
        int count = 0;
        while (count < 5) {
            String ship = Ships.values()[count].name;
            int shipCells = Ships.values()[count].cells;
            if (!sentError) {
                System.out.println("Enter the coordinates of the "+ ship + " (" + shipCells + " cells):");
            }
            String playerInput = input.nextLine().toUpperCase();
            Pattern pattern = Pattern.compile("^([A-J])(\\d{1,2}) ([A-J])(\\d{1,2})$");
            Matcher matcher = pattern.matcher(playerInput);
            int[] coordinates = new int[4];
            if (matcher.matches()) {
                coordinates[0] = (matcher.group(1).charAt(0) - 65);
                coordinates[1] = Integer.parseInt(matcher.group(2)) - 1;
                coordinates[2] = (matcher.group(3).charAt(0) - 65);
                coordinates[3] = Integer.parseInt(matcher.group(4)) - 1;
                //place the highest values at the end
                if (coordinates[2] < coordinates[0]) {
                    int temp = coordinates[2];
                    coordinates[2] = coordinates[0];
                    coordinates[0] = temp;
                }
                if (coordinates[1] > coordinates[3]) {
                    int temp = coordinates[3];
                    coordinates[3] = coordinates[1];
                    coordinates[1] = temp;
                }
            }
            boolean isHorizontal = coordinates[0] == coordinates[2];
            if (isCellTaken(field, coordinates)) {
                sentError = true;
                System.out.println("Error! You placed it too close to another one. Try again:");
            } else if (!isShipSize(shipCells, coordinates)) {
                sentError = true;
                System.out.println("Error! Wrong length of the " + ship + "! Try again:");
            } else if (!isGoodLocation(coordinates))  {
                sentError = true;
                System.out.println("Error! Wrong ship location! Try again:");
            } else {
                if (isHorizontal) {
                    int tempInitialCol = coordinates[1];
                    while (tempInitialCol <= coordinates[3]) {
                        field[coordinates[0]][tempInitialCol] = 'O';
                        tempInitialCol++;
                    }
                    sentError = false;
                } else {
                    int tempInitialRow = coordinates[0];
                    while (tempInitialRow <= coordinates[2]) {
                        field[tempInitialRow][coordinates[1]] = 'O';
                        tempInitialRow++;
                    }
                    sentError = false;

                }
                showFields(field);
                count++;
            }
        }
    }

    public static void showFields(char[][] fields) {
        StringBuilder result = new StringBuilder("  1 2 3 4 5 6 7 8 9 10");
        char rowChars = 65;
        for (char[] rows: fields) {
            result.append("\n").append(rowChars);
            for (char cell : rows) {
                if (cell == 0) {
                    result.append(" ").append("~");
                } else {
                    result.append(" ").append(cell);
                }
            }
            rowChars++;
        }
        System.out.println(result);
    }

    public static boolean isCellTaken(char[][] field, int[] coordinate) {
        char[][] clonedField = field.clone();
        boolean result = false;
        boolean isHorizontal = coordinate[0] == coordinate[2];
        // check if a cell is taken at the beginning coordinate - 1 more than 0 or end coordinate plus 1 less than length for horizontal. above and under
        if (isHorizontal) {
            int row = coordinate[0];
            int prevCell = coordinate[1] - 1 > 0? coordinate[1] - 1: coordinate[1];
            int end = coordinate[3] + 1 < field.length ? coordinate[3] + 1: coordinate[3];
            if (clonedField[row][prevCell] == 'O' || clonedField[row][end] == 'O') {
                result = true;
            }
            int initialCol = coordinate[1];
            while (initialCol <= coordinate[3]) { // check if any of the inner cells are taken
                if (clonedField[row][initialCol] == 'O') {
                    result = true;
                    break;
                }
                initialCol++;
            }
        } else {
            int col = coordinate[1];
            int prevCell = coordinate[0] - 1 > 0? coordinate[0] - 1: coordinate[0];
            int end = coordinate[2] + 1 < field.length ? coordinate[2] + 1: coordinate[2];
            if (clonedField[prevCell][col] == 'O' || clonedField[end][col] == 'O') {
                result = true;
            }
            int initialRow = coordinate[1];
            while (initialRow <= coordinate[3]) { // check if any of the inner cells are taken
                if (clonedField[initialRow][col] == 'O') {
                    result = true;
                    break;
                }
                initialRow++;
            }
        }

        return result;
    }

    public static boolean isShipSize(int cells, int[] coordinate) {
        int rowDifference = Math.abs(coordinate[0] - coordinate[2]) + 1;
        int colDifference = Math.abs(coordinate[1] - coordinate[3]) + 1;
        return cells == rowDifference || cells == colDifference;
    }

    public static boolean isGoodLocation(int[] coordinates) {
        return coordinates[0] == coordinates[2] || coordinates[1] == coordinates[3];
    }
}
