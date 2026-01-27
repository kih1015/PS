import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        // [1단계] 테스트 케이스 갯수 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        // [2단계] 테스트 케이스 처리
        long[] dp = new long[100 + 1];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        
        for (int i = 4; i <= 100; i++) {
            dp[i] = dp[i - 2] + dp[i - 3];
        }

        // [3단계] 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            sb.append(dp[Integer.parseInt(br.readLine())]).append("\n");
        }

        System.out.println(sb);
    }
}
