import java.io.*;
import java.util.Scanner;

public class Binary_2193 {

    public long run() {
        Scanner sc = new Scanner(System.in);
        return _run(sc.nextInt());
    }

    public long _run(int n) {
        if (n == 1) { return 1; }

        long[] dp = new long[n+1];
        dp[1] = 1; dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        return dp[n];
    }

    public static void main(String[] args) throws IOException {
        Binary_2193 m = new Binary_2193();
        System.out.println(m.run());
    }
}
