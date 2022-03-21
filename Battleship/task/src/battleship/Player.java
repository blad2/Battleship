package battleship;

import java.util.*;

public class Player {
    private final Grid shipPositions = new Grid(false);
    private final Grid shots = new Grid(false);
    private final List<Ships> sunkenShips = new ArrayList<>();
    private final String playerName;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public void placeShips(Scanner coordinates) {
        System.out.println(playerName + ", place your ships on the game field\n");
        shipPositions.showField();
        for (int i = 0; i < 5;) {
            shipPositions.placeShips(coordinates, i);
            i = shipPositions.getShipsCoordinates().size();
        }
    }

    // updated coordinates accordingly.
    // if sunk a ship, return sunk
    // else return you hit a ship
    public void hitShip (Scanner coordinates, Player opponent) {
        opponent.getShotsFieldsView();
        System.out.println("---------------------");
        shipPositions.showField();
        System.out.println();
        // I need to get the opponent shipPosition to match coordinate
        // when we sink a ship, we should return "You sank a ship!"
        System.out.println(playerName + ", it's your turn:\n");
        String cell = coordinates.nextLine().toUpperCase();
        int rowPosition = cell.charAt(0) - 65;
        int colPosition = Integer.parseInt(cell.substring(1).trim()) - 1;
        int sinkingShipSize = sunkenShips.size();
        if (opponent.getShipPositions().getFields()[rowPosition][colPosition] == 'O') {
            opponent.setShots(rowPosition, colPosition, 'X');
            checkSinkingShips(opponent);
            if (sunkenShips.size() == 5) {
                System.out.println("You sank the last ship. You won. Congratulations!");
            } else if (sunkenShips.size() > sinkingShipSize) {
                System.out.println("You sank a ship!\n");
            } else{
                System.out.println("You hit a ship!\n" + "Press Enter and pass the move to another player");
            }
        } else if (opponent.getShipPositions().getFields()[rowPosition][colPosition] == '~') {
            opponent.setShots(rowPosition, colPosition, 'M');
            System.out.println("You missed!\n" +
                    "Press Enter and pass the move to another player\n");
        }
    }

    // this method returns the sunk ship based on coordinates from shot and shipPositions
    public void checkSinkingShips(Player opponent) {
        // get the ship coordinate from shipPosition
        // if the coordinate are Ship x, then check if his positions are taken down
        for (Map.Entry<Ships, Integer[]> map: opponent.getShipPositionMap().entrySet()) {
            if (sunkenShips.contains(map.getKey())) {
                continue;
            }
            int row1 = map.getValue()[0];
            int col1 = map.getValue()[1];
            int row2 = map.getValue()[2];
            int col2 = map.getValue()[3];
            int hitCells = 0;
            if (row1 == row2) {
                for (int i = col1; i <= col2; i++) {
                    if (opponent.shipPositions.getFields()[row1][i] == 'X') { //checking if there's a ship in this coordinate
                        hitCells++;
                    }
                }
            } else {
                for (int i = row1; i <= row2; i++) {
                    if (opponent.shipPositions.getFields()[i][col1] == 'X') {
                        hitCells++;
                    }
                }
            }
            if (hitCells == map.getKey().cells) {
                sunkenShips.add(map.getKey());
                break;
            }
        }
    }

    public boolean won() {
        return sunkenShips.size() < 5;
    }

    public void setShots (int row, int col, char shotResult) {
        // shots.setFields(row, col, shotResult);
        shipPositions.setFields(row, col, shotResult);
    }

    public void getShotsFieldsView() {
        shots.showField();
    }

    public Map<Ships, Integer[]> getShipPositionMap() {
        return shipPositions.getShipsCoordinates();
    }

    public Grid getShipPositions() {
        return shipPositions;
    }


}
