import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
  public static void main(String[] args) throws Exception {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    // start coding here
    String[] words = reader.readLine().split(" +");
    int count = 0;
    for (String s: words) {
      if (s.length() != 0) {
        count++;
      }
    }
    System.out.println(count);
    reader.close();
  }
}