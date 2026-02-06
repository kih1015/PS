import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int INF = 1000000000;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 지역 갯수
        int m = Integer.parseInt(st.nextToken()); // 수색 범위
        int r = Integer.parseInt(st.nextToken()); // 길의 갯수

        int[] itemCount = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            itemCount[i] = Integer.parseInt(st.nextToken());
        }

        int[][] adj = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(adj[i], INF);
            adj[i][i] = 0;
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adj[from][to] = weight;
            adj[to][from] = weight;
        }
        
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
                }
            }
        }
        
        int maxItemCount = 0;
        for (int i = 1; i <= n; i++) {
            int currentItemCount = 0;
            
            for (int j = 1; j <= n; j++) {
                int dist = adj[i][j];
                
                if (dist == INF) {
                    continue;
                }
                
                if (dist > m) {
                    continue;
                }
                
                currentItemCount += itemCount[j];
            }
            
            maxItemCount = Math.max(maxItemCount, currentItemCount);
        }

        System.out.print(maxItemCount);
        br.close();
    }
}
