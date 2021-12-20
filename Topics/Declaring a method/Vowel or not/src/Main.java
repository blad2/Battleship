import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static boolean isVowel(char ch) {
        List<Character> chars = new ArrayList<>(List.of('a', 'e', 'i', 'o', 'u'));
        return chars.contains(Character.toLowerCase(ch));
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char letter = scanner.nextLine().charAt(0);
        System.out.println(isVowel(letter) ? "YES" : "NO");
    }
}