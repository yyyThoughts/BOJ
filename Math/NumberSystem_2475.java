import java.io.*;
import java.util.Scanner;

public class NumberSystem_2475 {

    private final int[] converter = new int[91];

    NumberSystem_2475() {
        int num = 0;
        for (int i = 48; i <= 57; i++) {
            converter[i] = num++;
        }
        num = 10;
        for (int i = 65; i <= 90; i++) {
            converter[i] = num++;
        }
    }

    public int run() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] strs = str.split(" ");
        return _run(strs[0], Integer.parseInt(strs[1]));
    }

    public int _run(String str, int digit) {
        StringBuilder sb = new StringBuilder(str);
        return __run(sb.reverse().toString(), digit, 0);
    }

    private int __run(String str, int b, int index) {
        int value = (int)Math.pow(b, index);
        if (str.length() == index + 1) {
            return converter[str.charAt(index)] * value;
        }
        return __run(str, b, index + 1) + converter[str.charAt(index)] * value;
    }

    public static void main(String[] args) throws IOException {
        NumberSystem_2475 m = new NumberSystem_2475();
        System.out.println(m.run());
    }
}
