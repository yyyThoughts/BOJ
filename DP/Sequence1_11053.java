import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Sequence1_11053 {
    int N;
    int[] arrays;

    public void read() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arrays = new int[N];
        for (int i = 0; i < N; i++) {
            arrays[i] = sc.nextInt();
        }
    }

    public void testRead(int N, int[] nums) {
        this.N = N;
        this.arrays = nums;
    }

    public int run() {
        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            dp[i] = 1;
        }

        for (int i = 0; i < N; i++) {
            int last = arrays[i];
            for (int j = 0; j < i; j++) {
                int target = arrays[j];
                if (last > target) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        Arrays.sort(dp);
        return dp[dp.length - 1];
    }

    public static void main(String[] args) throws IOException {
        Sequence1_11053 seq = new Sequence1_11053();
        seq.read();
        System.out.println(seq.run());
    }
}
