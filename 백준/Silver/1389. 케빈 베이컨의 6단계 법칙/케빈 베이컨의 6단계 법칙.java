import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // [1단계] n: 정점의 개수
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // [2단계] 인접 행렬 만들기
        int INF = 1000000;
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = INF;
            }
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st2.nextToken());
            int y = Integer.parseInt(st2.nextToken());

            matrix[x - 1][y - 1] = 1;
            matrix[y - 1][x - 1] = 1;
        }

        // [3단계] 플로이드-워셜
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }

        // [4단계] 케빈 베이컨 수 구하기
        int[][] kbCount = new int[n][2];

        for (int i = 0; i < n; i++) {
            int sum = 0;

            for (int j = 0; j < n; j++) {
                sum += matrix[i][j];
            }

            kbCount[i][0] = sum;
            kbCount[i][1] = i + 1;
        }

        // [5단계] 번호가 가장 작은 사람 찾기
        Arrays.sort(kbCount, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        // [6단계] 출력
        System.out.println(kbCount[0][1]);
        br.close();
    }
}
