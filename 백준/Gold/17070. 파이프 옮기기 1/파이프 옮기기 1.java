import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] pipeMap = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                pipeMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][][] dp = new int[n + 1][n + 1][3];
        dp[1][2][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (pipeMap[i][j] == 1) {
                    continue;
                }

                int flat = dp[i][j][0];
                int vertical = dp[i][j][1];
                int diagonal = dp[i][j][2];

                boolean isFlat = j + 1 <= n
                    && pipeMap[i][j + 1] == 0;
                boolean isVertical = i + 1 <= n
                    && pipeMap[i + 1][j] == 0;
                boolean isDiagonal = i + 1 <= n && j + 1 <= n
                    && pipeMap[i + 1][j] == 0
                    && pipeMap[i + 1][j + 1] == 0
                    && pipeMap[i][j + 1] == 0;

                if (flat > 0) {
                    if (isFlat) {
                        dp[i][j + 1][0] += flat;
                    }
                    if (isDiagonal) {
                        dp[i + 1][j + 1][2] += flat;
                    }
                }

                if (vertical > 0) {
                    if (isVertical) {
                        dp[i + 1][j][1] += vertical;
                    }
                    if (isDiagonal) {
                        dp[i + 1][j + 1][2] += vertical;
                    }
                }

                if (diagonal > 0) {
                    if (isFlat) {
                        dp[i][j + 1][0] += diagonal;
                    }
                    if (isVertical) {
                        dp[i + 1][j][1] += diagonal;
                    }
                    if (isDiagonal) {
                        dp[i + 1][j + 1][2] += diagonal;
                    }
                }
            }
        }

        System.out.println(dp[n][n][0] + dp[n][n][1] + dp[n][n][2]);
        br.close();
    }
}
