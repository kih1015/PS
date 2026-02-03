import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 물품의 수, 제한 무게 입력
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // 물건의 무게, 가치 입력
        int[][] goods = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            goods[i][0] = Integer.parseInt(st.nextToken());
            goods[i][1] = Integer.parseInt(st.nextToken());
        }

        // dp
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            int currentWeight = goods[i][0];
            int currentValue = goods[i][1];
            for (int j = 1; j <= k; j++) {
                if (currentWeight > j) {
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - currentWeight] + currentValue);
            }
        }
        
        // 최댓값 찾기
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= k; i++) {
            max = Math.max(max, dp[n][i]);
        }
        
        System.out.println(max);
        br.close();
    }
}
