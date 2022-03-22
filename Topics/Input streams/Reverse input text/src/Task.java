// write your answer here 


import java.io.InputStreamReader;
import java.io.Reader;

class Task {
    public static void main(String[] args) {
        // put your code here
        try {
            Reader reader = new InputStreamReader(System.in);
            int charText = reader.read();
            StringBuilder output = new StringBuilder();
            while (charText != -1) {
                output.append((char) charText);
                charText = reader.read();
            }
            reader.close();
            System.out.println(output.reverse());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
