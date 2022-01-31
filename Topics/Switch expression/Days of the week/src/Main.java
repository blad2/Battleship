import java.util.Scanner;

enum DaysOfTheWeek { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY }

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DaysOfTheWeek day = DaysOfTheWeek.valueOf(scanner.next().toUpperCase());
        int numLetters;

        // Put switch expression here
        numLetters = switch (day) { // this is for java 12 and above.
            case MONDAY, FRIDAY, SUNDAY -> 6;
            case TUESDAY -> 7;
            case THURSDAY, SATURDAY -> 8;
            case WEDNESDAY -> 9;
            default -> throw new IllegalStateException("Invalid day: " + day);
        };
        String html = String.format("""
                    \"<html>
                        <body>
                            <p>Today is %s <p>
                        </body>
                    </html>
                """, numLetters);
        System.out.println(html);
    }
}