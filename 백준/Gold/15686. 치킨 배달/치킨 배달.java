import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 도시 크기, 최대 치킨집 갯수 입력
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<int[]> home = new ArrayList<>();
        List<int[]> chicken = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int kind = Integer.parseInt(st.nextToken());
                if (kind == 1) {
                    home.add(new int[]{i, j});
                } else if (kind == 2) {
                    chicken.add(new int[]{i, j});
                }
            }
        }

        // 치킨집과의 거리
        int[][] distances = new int[home.size()][chicken.size()];
        for (int i = 0; i < home.size(); i++) {
            for (int j = 0; j < chicken.size(); j++) {
                int[] homePoint = home.get(i);
                int[] chickenPoint = chicken.get(j);

                int rowDistance = Math.abs(homePoint[0] - chickenPoint[0]);
                int colDistance = Math.abs(homePoint[1] - chickenPoint[1]);
                distances[i][j] = rowDistance + colDistance;
            }
        }

        System.out.println(dfs(0, new int[m], m, chicken.size(), 0, distances));
        br.close();
    }

    private static int dfs(int node, int[] array, int m, int chickenSize, int depth, int[][] distances) {
        if (depth == m) {
            int sum = 0;
            for (int[] chicken : distances) {
                int min = Integer.MAX_VALUE;
                for (int chickenNode : array) {
                    min = Math.min(min, chicken[chickenNode]);
                }
                sum += min;
            }
            return sum;
        }

        int min = Integer.MAX_VALUE;
        for (int i = node; i < chickenSize; i++) {
            array[depth] = i;
            min = Math.min(min, dfs(i + 1, array, m, chickenSize, depth + 1, distances));
        }
        return min;
    }
}
