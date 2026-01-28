import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // [1단계] 지도 크기 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // [2단계] 지도 입력 받기
        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st2.nextToken());
            }
        }

        // [3단계] bfs
        int[][] count = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();

        int startX = 0, startY = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 2) {
                    startX = i;
                    startY = j;
                    count[i][j] = 0;
                } else if (map[i][j] == 1) {
                    count[i][j] = -1;
                }
            }
        }

        queue.offer(new int[]{startX, startY});
        visited[startX][startY] = true;

        int[][] offsets = {
                {0, 1},  // 우
                {0, -1}, // 좌
                {1, 0},  // 하
                {-1, 0}  // 상
        };

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int[] offset : offsets) {
                int x = cur[0] + offset[0];
                int y = cur[1] + offset[1];

                if (check(map, x, y) && !visited[x][y]) {
                    visited[x][y] = true;
                    count[x][y] = count[cur[0]][cur[1]] + 1;
                    queue.offer(new int[]{x, y});
                }
            }
        }

        // [4단계] 출력
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(count[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    private static boolean check(int[][] map, int x, int y) {
        boolean isBounded = x >= 0 && y >= 0 && x < map.length && y < map[0].length;

        return isBounded && map[x][y] == 1;
    }
}
