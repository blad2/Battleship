package battleship;

import java.util.Scanner;

public class Battle {

    Battle(Scanner coordinates) {
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        player1.placeShips(coordinates);
        System.out.println("Press Enter and pass the move to another player");
        System.out.println("...");
        player2.placeShips(coordinates);
        System.out.println("Press Enter and pass the move to another player\n");
        boolean player1Played = false;
        while (player1.won() || player2.won()) {
            try {
                if (player1Played) {
                    player2.hitShip(coordinates, player1);
                    player1Played = false;
                }
                player1.hitShip(coordinates, player2);
                player1Played = true;
            }catch (StringIndexOutOfBoundsException | NullPointerException | NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
