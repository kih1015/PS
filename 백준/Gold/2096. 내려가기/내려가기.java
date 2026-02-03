import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] scores = new int[n + 1][3];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            scores[i][0] = Integer.parseInt(st.nextToken());
            scores[i][1] = Integer.parseInt(st.nextToken());
            scores[i][2] = Integer.parseInt(st.nextToken());
        }

        int[][] maxDp = new int[n + 1][3];
        int[][] minDp = new int[n + 1][3];

        for (int i = 1; i <= n; i++) {
            int leftMax = Math.max(maxDp[i - 1][0], maxDp[i - 1][1]);
            int rightMax = Math.max(maxDp[i - 1][1], maxDp[i - 1][2]);
            int finalMax = Math.max(leftMax, rightMax);
            maxDp[i][0] = scores[i][0] + leftMax;
            maxDp[i][1] = scores[i][1] + finalMax;
            maxDp[i][2] = scores[i][2] + rightMax;

            int leftMin = Math.min(minDp[i - 1][0], minDp[i - 1][1]);
            int rightMin = Math.min(minDp[i - 1][1], minDp[i - 1][2]);
            int finalMin = Math.min(leftMin, rightMin);
            minDp[i][0] = scores[i][0] + leftMin;
            minDp[i][1] = scores[i][1] + finalMin;
            minDp[i][2] = scores[i][2] + rightMin;
        }

        int leftMax = Math.max(maxDp[n][0], maxDp[n][1]);
        int rightMax = Math.max(maxDp[n][1], maxDp[n][2]);
        int finalMax = Math.max(leftMax, rightMax);

        int leftMin = Math.min(minDp[n][0], minDp[n][1]);
        int rightMin = Math.min(minDp[n][1], minDp[n][2]);
        int finalMin = Math.min(leftMin, rightMin);

        System.out.print(finalMax + " " + finalMin);
        br.close();
    }
}
