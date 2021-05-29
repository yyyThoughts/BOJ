import java.io.*;
import java.util.Scanner;

public class MakeOne_1463 {
    int num;
    long[] card;

    public void read() {
        Scanner sc = new Scanner(System.in);
        num = sc.nextInt();
        card = new long[num + 1];
        card[0] = 0;
        for (int i = 1; i <= num; i++) {
            card[i] = sc.nextInt();
        }
    }

    public long run() {
        long[] dp = new long[num + 1];
        dp[0] = 0;
        for(int i = 1; i <= num; i++) {
            dp[i] = card[i];
            for (int j = 0; j <= i; j++) {
                dp[i] = Math.max(dp[i], dp[i-j] + dp[j]);
            }
        }
        long max = 0;
        for (int i = 0; i <= num; i++) {
            max = Math.max(max, dp[i] + dp[num - i]);
        }

        return max;
    }

    public static void main(String[] args) throws IOException {
        MakeOne_1463 m = new MakeOne_1463();
        m.read();
        System.out.println(m.run());
    }
}
