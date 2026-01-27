import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        // [1단계] 정수 N 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // [2단계] 계단 입력
        int[] stairs = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        // [3단계] dp
        int[][] dp = new int[n + 1][3];
        dp[1][1] = stairs[1];
        for (int i = 2; i <= n; i++) {
            dp[i][1] = Math.max(dp[i - 2][1], dp[i - 2][2]) + stairs[i];
            dp[i][2] = dp[i - 1][1] + stairs[i];
        }

        // [4단계] 출력
        System.out.println(Math.max(dp[n][1], dp[n][2]));
        br.close();
    }
}
