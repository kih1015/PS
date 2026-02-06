import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        // 인접 행렬 초기화
        int[][] adj = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(adj[i], 100000000);
            adj[i][i] = 0;
        }

        // 인접 행렬 입력
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adj[start][end] = Math.min(adj[start][end], cost);
        }

        // 플로이드-워셜
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
                }
            }
        }

        // 최소비용 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (adj[i][j] == 100000000) {
                    sb.append(0).append(" ");
                    continue;
                }

                sb.append(adj[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}
