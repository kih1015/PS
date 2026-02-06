import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int[][] room = new int[r][c];
        int cleanerRowBottom = -1;

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());

                if (room[i][j] == -1) {
                    cleanerRowBottom = i;
                }
            }
        }

        // 공기청정기 T초 시뮬레이션
        for (int i = 0; i < t; i++) {
            room = spread(room);
            clean(room, cleanerRowBottom);
        }

        // 전체 먼지의 양 구하기
        int dustAmount = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (room[i][j] == -1) {
                    continue;
                }

                dustAmount += room[i][j];
            }
        }

        System.out.print(dustAmount);
        br.close();
    }

    private static int[][] spread(int[][] room) {
        int[][] spread = new int[room.length][room[0].length];

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (int i = 0; i < room.length; i++) {
            for (int j = 0; j < room[i].length; j++) {
                if (room[i][j] == -1) {
                    spread[i][j] = -1;
                    continue;
                }

                int spreadDust = room[i][j] / 5;

                int minusDust = 0;
                for (int k = 0; k < dx.length; k++) {
                    int newX = i + dx[k];
                    int newY = j + dy[k];

                    // 벽인 경우 확산하지 않음
                    boolean isBound = newX >= 0 && newY >= 0 && newX < room.length && newY < room[0].length;
                    if (!isBound) {
                        continue;
                    }

                    // 공기청정기면 확산하지 않음
                    if (room[newX][newY] == -1) {
                        continue;
                    }

                    spread[newX][newY] += spreadDust;
                    minusDust += spreadDust;
                }

                spread[i][j] += room[i][j] - minusDust;
            }
        }

        return spread;
    }

    private static void clean(int[][] room, int cleanerRowBottom) {
        int lastCol = room[0].length - 1;
        int cleanerRow = cleanerRowBottom - 1;

        for (int r = cleanerRow - 2; r >= 0; r--) {
            room[r + 1][0] = room[r][0];
        }
        for (int c = 1; c <= lastCol; c++) {
            room[0][c - 1] = room[0][c];
        }
        for (int r = 1; r <= cleanerRow; r++) {
            room[r - 1][lastCol] = room[r][lastCol];
        }
        for (int c = lastCol - 1; c >= 1; c--) {
            room[cleanerRow][c + 1] = room[cleanerRow][c];
        }
        room[cleanerRow][1] = 0;

        int lastRow = room.length - 1;

        for (int r = cleanerRowBottom + 2; r <= lastRow; r++) {
            room[r - 1][0] = room[r][0];
        }
        for (int c = 1; c <= lastCol; c++) {
            room[lastRow][c - 1] = room[lastRow][c];
        }
        for (int r = lastRow - 1; r >= cleanerRowBottom; r--) {
            room[r + 1][lastCol] = room[r][lastCol];
        }
        for (int c = lastCol - 1; c >= 1; c--) {
            room[cleanerRowBottom][c + 1] = room[cleanerRowBottom][c];
        }
        room[cleanerRowBottom][1] = 0;
    }
}
