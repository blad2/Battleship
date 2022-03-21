package battleship;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class Grid {
    private final char[][] fields = new char[10][10];
    private final Map<Ships, Integer[]> shipsCoordinates = new TreeMap<>();

    Grid(boolean showFields) {
        for (char[] field : fields) {
            Arrays.fill(field, '~');
        }
        if (showFields){
            showField();
        }
    }

    public void placeShips(Scanner coordinates, int shipNumber) {
        try {
            String ship = Ships.values()[shipNumber].name;
            int shipCells = Ships.values()[shipNumber].cells;
            System.out.println("Enter the coordinates of the " + ship + " (" + shipCells + " cells):\n");
            Pattern pattern = Pattern.compile("^([A-J])(\\d{1,2}) ([A-J])(\\d{1,2})$");
            Matcher matcher = pattern.matcher(coordinates.nextLine().toUpperCase());
            System.out.println();
            Integer[] cells = new Integer[4];
            if (matcher.matches()) {
                cells[0] = (matcher.group(1).charAt(0) - 65);
                cells[1] = Integer.parseInt(matcher.group(2)) - 1;
                cells[2] = (matcher.group(3).charAt(0) - 65);
                cells[3] = Integer.parseInt(matcher.group(4)) - 1;
                if (cells[2] < cells[0]) {
                    int temp = cells[2];
                    cells[2] = cells[0];
                    cells[0] = temp;
                }
                if (cells[1] > cells[3]) {
                    int temp = cells[3];
                    cells[3] = cells[1];
                    cells[1] = temp;
                }
            }
            boolean isHorizontal = cells[0].equals(cells[2]);
            if (isCellTaken(cells)) {
                System.out.println("Error! You placed it too close to another one. Try again:\n");
            } else if (!isShipSize(shipCells, cells)) {
                System.out.println("Error! Wrong length of the " + ship + "! Try again:\n");
            } else if (goodCoordinate(cells)) {
                System.out.println("Error! Wrong ship location! Try again:\n");
            } else {
                if (isHorizontal) {
                    int tempInitialCol = cells[1];
                    while (tempInitialCol <= cells[3]) {
                        fields[cells[0]][tempInitialCol] = 'O';
                        tempInitialCol++;
                    }
                } else {
                    int tempInitialRow = cells[0];
                    while (tempInitialRow <= cells[2]) {
                        fields[tempInitialRow][cells[1]] = 'O';
                        tempInitialRow++;
                    }
                }
                showField();
                shipsCoordinates.put(Ships.values()[shipNumber], cells);
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showField() {
        StringBuilder result = new StringBuilder("  1 2 3 4 5 6 7 8 9 10");
        char rowChars = 65;
        for (char[] rows: fields) {
            result.append("\n").append(rowChars);
            for (char cell : rows) {
                if (cell == '~') {
                    result.append(" ").append("~");
                } else {
                    result.append(" ").append(cell);
                }
            }
            rowChars++;
        }
        System.out.println(result);
    }

    public boolean isCellTaken(Integer[] coordinate) {
        boolean result = false;
        boolean isHorizontal = Objects.equals(coordinate[0], coordinate[2]);
        if (isHorizontal) {
            int row = coordinate[0];
            int prevCell = coordinate[1] - 1 > 0 ? coordinate[1] - 1 : coordinate[1];
            int end = coordinate[3] + 1 < fields.length ? coordinate[3] + 1 : coordinate[3];
            if (fields[row][prevCell] == 'O' || fields[row][end] == 'O') {
                result = true;
            }
            int initialCol = coordinate[1];
            while (initialCol <= coordinate[3]) { // check if any of the inner cells are taken
                if (fields[row][initialCol] == 'O') {
                    result = true;
                    break;
                }
                initialCol++;
            }
        } else {
            /* this is checking the surrounding of the ship
            * example 27 37 should have the following cells
            *  1 (B) for the previous rows, 7, 8 and 9, and B7 to E7, same for 8 and 9.
            *   1 2 3 4 5 6 7 8 9 10
                A ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                B ~ ~ ~ ~ ~ * * * ~ ~
                C ~ ~ ~ ~ ~ * 0 * ~ ~
                D ~ ~ ~ ~ ~ * 0 * ~ ~
                E ~ ~ ~ ~ ~ * * * ~ ~
                F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                * */
            int col = coordinate[1];
            int prevRow = coordinate[0] - 1 > 0? coordinate[0] - 1: coordinate[0];
            int endRow = coordinate[2] + 1 < fields.length ? coordinate[2] + 1 : coordinate[2];
            int initialRow = prevRow;
            while (initialRow <= endRow) {
                if (fields[initialRow][col - 1 < 0 ? col : col - 1] == 'O'
                        || fields[initialRow][col + 1 > fields.length ? col : col + 1] == 'O') {
                    result = true;
                    break;
                }
                initialRow++;
            }
            if (fields[prevRow][col] == 'O' || fields[endRow][col] == 'O') {
                result = true;
            }
        }
        return result;
    }

    public static boolean isShipSize(int cells, Integer[] coordinate) {
        int rowDifference = Math.abs(coordinate[0] - coordinate[2]) + 1;
        int colDifference = Math.abs(coordinate[1] - coordinate[3]) + 1;
        return cells == rowDifference || cells == colDifference;
    }

    public char[][] getFields() {
        return fields.clone();
    }

    public void setFields(int row, int col, char variable) {
        fields[row][col] = variable;
    }

    public Map<Ships, Integer[]> getShipsCoordinates() {
        return shipsCoordinates;
    }

    public boolean goodCoordinate(Integer[] cells) {
        return !cells[0].equals(cells[2]) && !cells[1].equals(cells[3]);
    }

}
