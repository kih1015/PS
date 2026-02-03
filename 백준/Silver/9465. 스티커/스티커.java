import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // 테스트 케이스 갯수 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[][] scores = new int[2][n];

            // 스티커 점수 입력
            for (int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    scores[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 1열인 경우 처리
            if (n == 1) {
                sb.append(Math.max(scores[0][0], scores[1][0])).append("\n");
                continue;
            }

            // 1열 초기값
            scores[0][1] += scores[1][0];
            scores[1][1] += scores[0][0];

            // dp
            for (int j = 2; j < n; j++) {
                scores[0][j] += Math.max(scores[1][j - 1], scores[1][j - 2]);
                scores[1][j] += Math.max(scores[0][j - 1], scores[0][j - 2]);
            }

            // max 계산
            int max = Math.max(scores[0][n - 1], scores[1][n - 1]);
            sb.append(max).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}
