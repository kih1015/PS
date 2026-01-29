import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // [1단계] 집터 크기, 블럭 개수 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        // [2단계] 집터 입력 받기
        int[][] land = new int[n][m];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                land[i][j] = Integer.parseInt(st2.nextToken());

                if (land[i][j] < min) {
                    min = land[i][j];
                }

                if (land[i][j] > max) {
                    max = land[i][j];
                }
            }
        }

        // [3단계] 최소 시간 및 땅의 높이 계산하기
        int minTime = Integer.MAX_VALUE;
        int minTimeHeight = 0;

        for (int height = min; height <= max; height++) {
            int spendingTime = 0;
            int inventory = b;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (land[i][j] == height) {
                        continue;
                    }

                    int difference = Math.abs(land[i][j] - height);

                    if (land[i][j] > height) {
                        spendingTime += (2 * difference);
                        inventory += difference;
                        continue;
                    }

                    spendingTime += difference;
                    inventory -= difference;
                }
            }

            if (inventory < 0) {
                continue;
            }

            if (spendingTime == minTime) {
                if (minTimeHeight < height) {
                    minTimeHeight = height;
                }
                continue;
            }

            if (spendingTime < minTime) {
                minTime = spendingTime;
                minTimeHeight = height;
            }
        }

        // [4단계] 출력
        StringBuilder sb = new StringBuilder();
        sb.append(minTime).append(" ").append(minTimeHeight);
        System.out.println(sb);
    }
}
