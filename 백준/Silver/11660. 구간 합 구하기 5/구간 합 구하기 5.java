import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // 표의 크기, 합을 구해야 하는 횟수 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 표 입력 + dp
        int[][] table = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                table[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp
        for (int i = 1; i <= n; i++) {
            for (int j = 2; j <= n; j++) {
                table[i][j] += table[i][j - 1];
            }
        }

        // 합 계산
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int sum = 0;
            for (int j = x1; j <= x2; j++) {
                sum += table[j][y2] - table[j][y1 - 1];
            }

            sb.append(sum).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}
