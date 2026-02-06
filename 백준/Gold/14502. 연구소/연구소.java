import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];

        List<int[]> birus = new ArrayList<>(10);
        List<int[]> empty = new ArrayList<>(n * m);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int thing = Integer.parseInt(st.nextToken());
                map[i][j] = thing;

                if (thing == 2) {
                    birus.add(new int[]{i, j});
                    continue;
                }

                if (thing == 0) {
                    empty.add(new int[]{i, j});
                }
            }
        }

        int max = 0;

        for (int i = 0; i < empty.size(); i++) {
            for (int j = i + 1; j < empty.size(); j++) {
                for (int k = j + 1; k < empty.size(); k++) {
                    int[][] addedWalls = new int[3][2];

                    addedWalls[0][0] = empty.get(i)[0];
                    addedWalls[0][1] = empty.get(i)[1];

                    addedWalls[1][0] = empty.get(j)[0];
                    addedWalls[1][1] = empty.get(j)[1];

                    addedWalls[2][0] = empty.get(k)[0];
                    addedWalls[2][1] = empty.get(k)[1];

                    int[][] copiedMap = new int[n][m];
                    for (int l = 0; l < n; l++) {
                        copiedMap[l] = map[l].clone();
                    }

                    for (int l = 0; l < birus.size(); l++) {
                        bfs(copiedMap, addedWalls, new int[] {birus.get(l)[0], birus.get(l)[1]});
                    }

                    max = Math.max(max, searchSafeZone(copiedMap, addedWalls));
                }
            }
        }

        System.out.print(max);
        br.close();
    }

    private static void bfs(int[][] map, int[][] addedWalls, int[] start) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            for (int i = 0; i < dx.length; i++) {
                int nextX = curr[0] + dx[i];
                int nextY = curr[1] + dy[i];

                boolean isBound = nextX >= 0 && nextX < map.length && nextY >= 0 && nextY < map[0].length;
                if (!isBound) {
                    continue;
                }

                if (isAddedWalls(nextX, nextY, addedWalls)) {
                    continue;
                }

                if (map[nextX][nextY] == 0) {
                    map[nextX][nextY] = 2;
                    queue.offer(new int[]{nextX, nextY});
                }
            }
        }
    }

    private static int searchSafeZone(int[][] map, int[][] addedWalls) {
        int count = 0;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (isAddedWalls(i, j, addedWalls)) {
                    continue;
                }
                
                if (map[i][j] == 0) {
                    count++;
                }
            }
        }

        return count;
    }
    
    private static boolean isAddedWalls(int row, int col, int[][] addedWalls) {
        for (int k = 0; k < 3; k++) {
            if (addedWalls[k][0] == row && addedWalls[k][1] == col) {
                return true;
            }
        }
        
        return false;
    }
}
