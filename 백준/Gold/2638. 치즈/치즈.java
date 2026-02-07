import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] paper = new int[n][m];
        int cheeseCount = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
                
                if (paper[i][j] == 1) {
                    cheeseCount++;
                }
            }
        }
        
        int time = 0;
        while (cheeseCount > 0) {
            blow(paper);
            cheeseCount -= melt(paper);
            
            time++;
        }

        System.out.print(time);
        br.close();
    }

    private static void blow(int[][] paper) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0});

        boolean[][] visited = new boolean[paper.length][paper[0].length];
        visited[0][0] = true;

        int[] dx = new int[] {-1, 1, 0, 0};
        int[] dy = new int[] {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for (int k = 0; k < 4; k++) {
                int nextX = x + dx[k];
                int nextY = y + dy[k];

                boolean isBound = nextX >= 0 && nextX < paper.length && nextY >= 0 && nextY < paper[0].length;
                if (!isBound || visited[nextX][nextY]) {
                    continue;
                }

                if (paper[nextX][nextY] != 1) {
                    visited[nextX][nextY] = true;
                    queue.offer(new int[] {nextX, nextY});

                    // 바깥 공기: '2', 안쪽 공기: '0'
                    paper[nextX][nextY] = 2;
                }
            }
        }
    }

    private static int melt(int[][] paper) {
        int[] dx = new int[] {-1, 1, 0, 0};
        int[] dy = new int[] {0, 0, -1, 1};

        int meltCount = 0;
        for (int i = 0; i < paper.length; i++) {
            for (int j = 0; j < paper[i].length; j++) {
                if (paper[i][j] != 1) {
                    continue;
                }

                int count = 0;
                for (int k = 0; k < 4; k++) {
                    int nextX = i + dx[k];
                    int nextY = j + dy[k];

                    if (paper[nextX][nextY] == 2) {
                        count++;
                    }
                }

                if (count >= 2) {
                    paper[i][j] = 0;
                    meltCount++;
                }
            }
        }

        return meltCount;
    }
}
