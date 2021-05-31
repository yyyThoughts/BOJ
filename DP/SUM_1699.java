import java.io.*;
import java.util.Scanner;

public class SUM_1699 {
    int N;

    public void read() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
    }

    public int run() {
        int[] dp = new int[N+1];
        for (int i = 1; i <= N; i++) {
            dp[i] = i;
            for (int j = 1; j*j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j*j] + 1);
            }
        }
        return dp[N];
    }

    public static void main(String[] args) throws IOException {
        SUM_1699 m = new Main();
        m.read();
        System.out.println(m.run());
    }
}
