import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int[][] space;
    private static int n;
    private static int[] dx = {0, -1, 0, 1};
    private static int[] dy = {1, 0, -1, 0};

    private static int babySharkSize;
    private static int eatenFishCount;

    private static int time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        space = new int[n][n];

        int[] current = null;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());

                if (space[i][j] == 9) {
                    current = new int[]{i, j};
                }
            }
        }

        babySharkSize = 2;
        eatenFishCount = 0;
        
        time = 0;

        while (current != null) {
            current = eatFish(current[0], current[1]);

            if (babySharkSize == eatenFishCount) {
                babySharkSize++;
                eatenFishCount = 0;
            }
        }

        System.out.print(time);
        br.close();
    }

    private static int[] eatFish(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});

        boolean[][] visited = new boolean[n][n];
        visited[x][y] = true;

        List<int[]> found = new LinkedList<>();

        int spentTime = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();

                for (int j = 0; j < 4; j++) {
                    int nextX = curr[0] + dx[j];
                    int nextY = curr[1] + dy[j];

                    if (!isBound(nextX, nextY) || visited[nextX][nextY]) {
                        continue;
                    }

                    if (space[nextX][nextY] > babySharkSize) {
                        continue;
                    }

                    if (space[nextX][nextY] > 0 && space[nextX][nextY] < babySharkSize) {
                        found.add(new int[]{nextX, nextY});
                        visited[nextX][nextY] = true;
                        continue;
                    }

                    visited[nextX][nextY] = true;
                    queue.offer(new int[]{nextX, nextY});
                }
            }

            spentTime++;

            if (found.isEmpty()) {
                continue;
            }

            int row = Integer.MAX_VALUE;
            int col = Integer.MAX_VALUE;

            for (int[] point : found) {
                int currentRow = point[0];
                int currentCol = point[1];

                if (currentRow > row) {
                    continue;
                }

                if (currentRow < row) {
                    row = currentRow;
                    col = currentCol;
                    continue;
                }

                if (currentCol < col) {
                    row = currentRow;
                    col = currentCol;
                }
            }

            space[x][y] = 0;
            space[row][col] = 9;

            eatenFishCount++;

            time += spentTime;

            return new int[]{row, col};
        }

        return null;
    }

    private static boolean isBound(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}
