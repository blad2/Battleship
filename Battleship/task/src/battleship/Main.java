package battleship;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Board board = new Board();
        board.placeShip(input);
        board.print();
    }
}

class Board {
    private List<Row> boardSet =  List.of(new Row('A'), new Row('B'), new Row('C'),
            new Row('D'), new Row('E'), new Row('F'), new Row('G'),
            new Row('H'), new Row('I'), new Row('J'));
    private Ships ships;
    // column/row 10x10 field create the field.
    // receives the fields selected by the player and show presents the structure
    // a methods to Add the coordinates chosen
    Board() {
        StringBuilder sb = new StringBuilder();
        sb.append("  1 2 3 4 5 6 7 8 9 10\n");
        for (Row r: boardSet) {
            sb.append(r.getCharacter());
            for (int i = 0; i < r.getColumns().length; i++) {
                sb.append(" ").append(r.getColumns()[i] == 0 ? "~" : r.getColumns()[i]);
                if (i == r.getColumns().length - 1) {
                    sb.append("\n");
                }
            }
        }
        System.out.println(sb);
    }

    public void print() {
        StringBuilder sb = new StringBuilder();
        sb.append("  1 2 3 4 5 6 7 8 9 10\n");
        for (Row r: boardSet) {
            sb.append(r.getCharacter());
            for (int i = 0; i < r.getColumns().length; i++) {
                sb.append(" ").append(r.getColumns()[i] == 0 ? "~" : r.getColumns()[i]);
                if (i == r.getColumns().length - 1) {
                    sb.append("\n");
                }
            }
        }
        System.out.println(sb);
    }

    public void checkPosition(String coordinates) {
        char fChar = coordinates.charAt(0);
        char sChar = coordinates.charAt(3);
        int fNumber = Character.getNumericValue(coordinates.charAt(1));
        int sNumber = Character.getNumericValue(coordinates.charAt(4));


    }

    public void placeShip(Scanner input) {
        List<String> ships = new ArrayList<>();
        int count = 0;
        while (count < 5){
            String ship = Ships.getNameByIndex(count);
            int shipCells = Ships.getCells(ship);
            System.out.println("Enter the coordinates of the " + ship + " (" + shipCells + " cells):\n");
            String coordinates = input.nextLine();
            char begLetterCoordinate = coordinates.charAt(0); // letter of beginning of the coordinate
            int begNumber = Character.getNumericValue(coordinates.charAt(1));
            char endLetterCoordinate = coordinates.charAt(3); //letter of end of the coordinate
            int endNumber = Character.getNumericValue(coordinates.charAt(4));
            // if the coordinate length is higher than the ship, return "Error! Wrong length of the Submarine! Try again:"
            if (Math.abs(begNumber - endNumber + 1) == shipCells
                    || Math.abs(begLetterCoordinate - endLetterCoordinate + 1) == shipCells) {
                ships.add(ship);
                count++;
            } else {
                System.out.println("Error! Wrong length of the Submarine! Try again:");
                break;
            }
            // if the coordinate is one off of any other ship return "Error! You placed it too close to another one. Try again:"
            for (Row row : boardSet) {
                // Getting coordinates vertically
                if (begNumber == endNumber && row.isFieldTaken(begNumber - 1)) {
                    // if coordinates hit on another ship (a row column with its index with 'O') return "Error! Wrong ship location! Try again:"
                    row.setColumns(begNumber - 1, 'O');
                    if (row.character == endLetterCoordinate) {
                        continue;
                    }
                } else {
                    System.out.println("Error! Wrong ship location! Try again:");
                    break;
                }
                // Getting the coordinates horizontally
                if (begLetterCoordinate == endLetterCoordinate
                        && row.character.equals(begLetterCoordinate)) {
                    int beginning = Character.getNumericValue(begLetterCoordinate);
                    int end = Character.getNumericValue(endLetterCoordinate);
                    for (int j = beginning - 1; j <= end; j++) {
                        row.setColumns(j, 'O');
                    }
                }
            }
        }
    }

    class Row {
        private char[] columns = new char[10];
        private Character character;

        Row(char r) {
            this.character = r;
        }

        public char[] getColumns() {
            return columns;
        }

        public void setColumns(int position, char symbol) {
            this.columns[position] = symbol;
        }

        public boolean isFieldTaken(int field) {
            return columns[field] == 0;
        }

        public Character getCharacter() {
            return character;
        }

    }
}

enum Ships {
    AIRCRAFT_CARRIER(5,"Aircraft Carrier"),
    BATTLESHIP(4,"Battleship"),
    SUBMARINE(3, "Submarine"),
    CRUISER(3, "Cruiser"),
    DESTROYER(2, "Destroyer");

    private int cells;
    private String shipName;

    Ships(int cells, String shipName) {
        this.cells = cells;
        this.shipName = shipName;
    }

    public static int getCells(String shipName) {
        return Ships.valueOf(shipName).cells;
    }

    public static String getNameByIndex(int index) {
         return Ships.values()[index].name();
    }
}
