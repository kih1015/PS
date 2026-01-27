import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        // [1단계] n 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // [2단계] dp
        int[] dp = new int[50000 + 1];

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        dp[4] = 1;

        for (int i = 5; i <= n; i++) {
            int j = 1;
            int min = Integer.MAX_VALUE;
            while (j * j <= i) {
                if (min > dp[i - j * j]) {
                    min = dp[i - j * j];
                }
                j++;
            }
            dp[i] = min + 1;
        }

        // [3단계] 출력
        System.out.println(dp[n]);
    }
}
