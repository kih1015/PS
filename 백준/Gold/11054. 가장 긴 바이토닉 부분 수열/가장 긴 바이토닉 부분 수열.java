import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int[] reverseDp = new int[n];
        Arrays.fill(reverseDp, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (a[i] > a[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }

                int reverseI = n - 1 - i;
                int reverseJ = n - 1 - j;
                if (a[reverseI] > a[reverseJ]) {
                    reverseDp[reverseI] = Math.max(reverseDp[reverseI], reverseDp[reverseJ] + 1);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, dp[i] + reverseDp[i] - 1);
        }

        System.out.print(max);
        br.close();
    }
}
