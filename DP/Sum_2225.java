import java.io.*;
import java.util.Scanner;

public class Sum_2225 {
    int N;
    int K;

    public void read() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
    }

    public int value(int n, int k) {
        int sum = 0;
        if (n == 1) return k;
        else {
            for (int i = 0; i <= k; i++) {
                sum += value(n-1, i);
            }
        }
        return sum % 1000000000;
    }

    public long run() {
        long divisor = 1000000000;
        long[][] dp = new long[K + 1][N + 1];

        for (int n = 0; n <= N; n++) {
            dp[1][n] = 1;
        }

        for (int k = 0; k <= K; k++) {
            dp[k][0] = 1;
            dp[k][1] = k;
        }
        dp[0][1] = 0;


        for (int k = 2; k <= K; k++) {
            for (int n = 2; n <= N; n++) {
                for (int t = 0; t <= n; t++) {
                    dp[k][n] += dp[k-1][n-t];
                }

                dp[k][n] %= divisor;
            }
        }
        return dp[K][N];
    }

    public long run2() {
        return value(N, K);
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.read();
        System.out.println(m.run());
//        System.out.println(m.run2());
    }
}
