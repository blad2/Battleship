package battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Player player1 = new Player();
        player1.startGame(input);
    }
}

class Grid {
    private char[][] fields = new char[10][10];
    private List<Ships> shipList = new ArrayList<>(); //

    Grid() {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                fields[i][j] = '~';
            }
        }
        staticShowFields(fields);
    }
    Grid(boolean showFields) {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                fields[i][j] = '~';
            }
        }
        if (showFields){
            staticShowFields(fields);
        }
    }

    public void takeAShot(Scanner shotLocation) {
        boolean shotTaken = false;
        while (!shotTaken) {
            String shot = shotLocation.nextLine().toUpperCase();
            int rowTarget = shot.charAt(0) - 65;
            int colTarget = Integer.parseInt(shot.substring(1).trim()) - 1;
            if (colTarget > 9 || rowTarget > 9) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
            } else if (fields[rowTarget][colTarget] == 'O') {
                System.out.println("You hit a ship!");
                fields[rowTarget][colTarget] = 'X';
                staticShowFields(fields);
                shotTaken = true;
            } else if (fields[rowTarget][colTarget] == '~') {
                System.out.println("You missed!");
                fields[rowTarget][colTarget] = 'M';
                staticShowFields(fields);
                shotTaken = true;
            }
        }
    }

    public String takeAShot(String shotLocation) {
        String result = "";
        boolean shotTaken = false;
        while (!shotTaken) {
            String shot = shotLocation.toUpperCase();
            int rowTarget = shot.charAt(0) - 65;
            int colTarget = Integer.parseInt(shot.substring(1).trim()) - 1;
            if (colTarget > 9 || rowTarget > 9) {
                result = "error";
            } else if (fields[rowTarget][colTarget] == 'O') {
                result = "You hit a ship!";
                fields[rowTarget][colTarget] = 'X';
                staticShowFields(fields);
                shotTaken = true;
            } else if (fields[rowTarget][colTarget] == '~') {
                result = "missed";
                fields[rowTarget][colTarget] = 'M';
                staticShowFields(fields);
                shotTaken = true;
            }
        }
        return result;
    }

    public void placeShips(Scanner coordinates) {
        boolean sentError = false;
        int count = 0;
        while (count < 5) {
            String ship = Ships.values()[count].name;
            int shipCells = Ships.values()[count].cells;
            if (!sentError) {
                System.out.println("Enter the coordinates of the "+ ship + " (" + shipCells + " cells):\n");
            }
            Pattern pattern = Pattern.compile("^([A-J])(\\d{1,2}) ([A-J])(\\d{1,2})$");
            Matcher matcher = pattern.matcher(coordinates.nextLine().toUpperCase());
            System.out.println();
            int[] cells = new int[4];
            if (matcher.matches()) {
                cells[0] = (matcher.group(1).charAt(0) - 65);
                cells[1] = Integer.parseInt(matcher.group(2)) - 1;
                cells[2] = (matcher.group(3).charAt(0) - 65);
                cells[3] = Integer.parseInt(matcher.group(4)) - 1;
                //place the highest values at the end
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
            boolean isHorizontal = cells[0] == cells[2];
            if (isCellTaken(fields, cells)) {
                sentError = true;
                System.out.println("Error! You placed it too close to another one. Try again:\n");
            } else if (!isShipSize(shipCells, cells)) {
                sentError = true;
                System.out.println("Error! Wrong length of the " + ship + "! Try again:\n");
            } else if (!isGoodLocation(cells))  {
                sentError = true;
                System.out.println("Error! Wrong ship location! Try again:\n");
            } else {
                if (isHorizontal) {
                    int tempInitialCol = cells[1];
                    while (tempInitialCol <= cells[3]) {
                        fields[cells[0]][tempInitialCol] = 'O';
                        tempInitialCol++;
                    }
                    sentError = false;
                } else {
                    int tempInitialRow = cells[0];
                    while (tempInitialRow <= cells[2]) {
                        fields[tempInitialRow][cells[1]] = 'O';
                        tempInitialRow++;
                    }
                    sentError = false;
                }
                staticShowFields(fields);
                count++;
            }
        }
    }

    public static void staticShowFields(char[][] fields) {
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
        System.out.println(result + "\n");
    }

    public void showFields() {
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
        System.out.println(result + "\n");
    }

    public static boolean isCellTaken(char[][] field, int[] coordinate) {
        boolean result = false;
        boolean isHorizontal = coordinate[0] == coordinate[2];
        if (isHorizontal) {
            int row = coordinate[0];
            int prevCell = coordinate[1] - 1 > 0 ? coordinate[1] - 1 : coordinate[1];
            int end = coordinate[3] + 1 < field.length ? coordinate[3] + 1 : coordinate[3];
            if (field[row][prevCell] == 'O' || field[row][end] == 'O') {
                result = true;
            }
            int initialCol = coordinate[1];
            while (initialCol <= coordinate[3]) { // check if any of the inner cells are taken
                if (field[row][initialCol] == 'O') {
                    result = true;
                    break;
                }
                initialCol++;
            }
        } else {
            int col = coordinate[1];
            int prevCell = coordinate[0] - 1 > 0? coordinate[0] - 1: coordinate[0];
            int end = coordinate[2] + 1 < field.length ? coordinate[2] + 1 : coordinate[2];
            if (field[prevCell][col] == 'O' || field[end][col] == 'O') {
                result = true;
            }
            int initialRow = coordinate[1];
            while (initialRow <= coordinate[3]) { // check if any of the inner cells are taken
                if (field[initialRow][col] == 'O') {
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
        return (coordinates[0] == coordinates[2] || coordinates[1] == coordinates[3])
                && (coordinates[0] < 10 || coordinates[1] < 10 || coordinates[2] < 10 || coordinates[3] < 10)
                && (coordinates[0] > 0 || coordinates[1] > 0 || coordinates[2] > 0 || coordinates[3] > 0);
    }

    public char[][] getFields() {
        return fields;
    }
}

class Player {
    private Grid shipPositions = new Grid();
    private Grid shots = new Grid(false);

    public void setShipPositions(Scanner coordinates) {
        shipPositions.placeShips(coordinates);
    }

    public void startGame(Scanner coordinates) {
        setShipPositions(coordinates);
        System.out.println("The game starts!\n");
        shots.showFields();
        System.out.println("Take a shot!\n");
        boolean hitShip = true;
        while (hitShip) {
            try {
                String cell = coordinates.nextLine().toUpperCase();
                int rowPosition = cell.charAt(0) - 65;
                int colPosition = Integer.parseInt(cell.substring(1).trim()) - 1;
                if (shipPositions.getFields()[rowPosition][colPosition] == 'O') {
                    shots.getFields()[rowPosition][colPosition] = 'X';
                    shots.showFields();
                    System.out.println("You hit a ship! Try again:\n");
                    shipPositions.showFields();
                } else if (shipPositions.getFields()[rowPosition][colPosition] == '~') {
                    shots.getFields()[rowPosition][colPosition] = 'M';
                    shots.showFields();
                    System.out.println("You missed. Try again\n");
                    shipPositions.getFields()[rowPosition][colPosition] ='M';
                    shipPositions.showFields();
                    hitShip = false;// temporary for this test
                }
            } catch (Exception e) {
                System.out.println("Error! You entered wrong coordinates! Try again:\n");

            }
        }
    }

}

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