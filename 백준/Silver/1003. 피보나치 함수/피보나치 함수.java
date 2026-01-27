import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Count {
    int zero;
    int one;

    Count(int zero, int one) {
        this.zero = zero;
        this.one = one;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // [1단계] 객체 배열 생성 (최대 40까지 저장)
        Count[] dp = new Count[41];

        // [2단계] 초기값 설정
        dp[0] = new Count(1, 0);
        dp[1] = new Count(0, 1);

        // [3단계] 점화식 계산
        for (int i = 2; i <= 40; i++) {
            int totalZero = dp[i - 1].zero + dp[i - 2].zero;
            int totalOne = dp[i - 1].one + dp[i - 2].one;

            dp[i] = new Count(totalZero, totalOne);
        }

        // [4단계] 출력 처리
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n].zero).append(" ").append(dp[n].one).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}
