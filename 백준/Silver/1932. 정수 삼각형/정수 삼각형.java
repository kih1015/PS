import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // 삼각형의 크기 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // dp
        int[][] triangle = new int[n][n];
        triangle[0][0] = Integer.parseInt(br.readLine());
        for (int i = 1; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < i + 1; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
                if (j - 1 < 0) {
                    triangle[i][j] += triangle[i - 1][j];
                    continue;
                }
                triangle[i][j] += Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]);
            }
        }

        // 최댓값 계산
        int max = Integer.MIN_VALUE;
        for (int value : triangle[n - 1]) {
            max = Math.max(max, value);
        }

        // 최댓값 출력
        System.out.println(max);
        br.close();
    }
}