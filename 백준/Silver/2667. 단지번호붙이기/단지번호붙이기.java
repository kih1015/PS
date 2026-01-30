import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        // [1단계] 지도 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] danji = new int[n][n];
        for (int i = 0; i < n; i++) {
            String string = br.readLine();
            for (int j = 0; j < n; j++) {
                danji[i][j] = Character.getNumericValue(string.charAt(j));
            }
        }

        // [2단계] bfs
        List<Integer> danjiCount = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (danji[i][j] == 0) {
                    continue;
                }
                danjiCount.add(bfs(danji, i, j));
            }
        }

        // [3단계] 출력
        System.out.println(danjiCount.size());
        Collections.sort(danjiCount);
        for (int i = 0; i < danjiCount.size(); i++) {
            System.out.println(danjiCount.get(i));
        }
        br.close();
    }

    private static int bfs(int[][] danji, int startX, int StartY) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, StartY});
        danji[startX][StartY] = 0;
        int count = 1;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = current[0] + dx[i];
                int nextY = current[1] + dy[i];

                boolean canMove = nextX >= 0 && nextX < danji.length && nextY >= 0 && nextY < danji[0].length;
                if (canMove && danji[nextX][nextY] == 1) {
                    danji[nextX][nextY] = 0;
                    queue.offer(new int[]{nextX, nextY});
                    count++;
                }
            }
        }

        return count;
    }
}
