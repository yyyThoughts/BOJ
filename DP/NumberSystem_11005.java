import java.io.*;
import java.util.Scanner;

public class NumberSystem_11005 {

    public String run() {
        Scanner sc = new Scanner(System.in);
        return _run(sc.nextInt(), sc.nextInt()).reverse().toString();
    }

    public StringBuilder _run(int n, int b) {
        if (n < b) {
            return new StringBuilder().append((Character.toUpperCase(Character.forDigit(n, b))));
        } else {
            return new StringBuilder().
                    append(Character.toUpperCase(Character.forDigit(n % b, b))).
                    append(_run(n / b, b));
        }
    }

    public static void main(String[] args) throws IOException {
        NumberSystem_11005 m = new NumberSystem_11005();
        System.out.println(m.run());
    }
}
