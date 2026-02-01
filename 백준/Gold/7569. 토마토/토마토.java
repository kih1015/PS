import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // [1단계] m, n, h 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        // [2단계] 박스 입력받기
        Queue<int[]> queue = new LinkedList<>();
        int[][][] box = new int[h][n][m];
        int notRipedCount = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < m; k++) {
                    box[i][j][k] = Integer.parseInt(st.nextToken());
                    if (box[i][j][k] == 1) {
                        queue.add(new int[]{i, j, k});
                    } else if (box[i][j][k] == 0) {
                        notRipedCount++;
                    }
                }
            }
        }

        // [3단계] bfs
        int[] dr = {1, 0, -1, 0, 0, 0};
        int[] dc = {0, 1, 0, -1, 0, 0};
        int[] dh = {0, 0, 0, 0, 1, -1};
        int time = 0;
        while (!queue.isEmpty()) {
            if (notRipedCount == 0) {
                break;
            }

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                for (int j = 0; j < dr.length; j++) {
                    int nextH = current[0] + dh[j];
                    int nextR = current[1] + dr[j];
                    int nextC = current[2] + dc[j];

                    boolean isRowBounded = nextR >= 0 && nextR < n;
                    boolean isColBounded = nextC >= 0 && nextC < m;
                    boolean isHeightBounded = nextH >= 0 && nextH < h;
                    boolean isBounded = isRowBounded && isColBounded && isHeightBounded;

                    if (isBounded && box[nextH][nextR][nextC] == 0) {
                        box[nextH][nextR][nextC] = 1;
                        notRipedCount--;
                        queue.offer(new int[]{nextH, nextR, nextC});
                    }
                }
            }
            time++;
        }

        // [4단계] 출력
        boolean isNotAllRiped = notRipedCount != 0;
        if (isNotAllRiped) {
            time = -1;
        }
        System.out.println(time);
    }
}
