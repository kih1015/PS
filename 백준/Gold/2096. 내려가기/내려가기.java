import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] maxDp = new int[3];
        int[] minDp = new int[3];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (i == 0) {
                maxDp[0] = minDp[0] = a;
                maxDp[1] = minDp[1] = b;
                maxDp[2] = minDp[2] = c;
                continue;
            }

            int prevMax0 = maxDp[0], prevMax1 = maxDp[1], prevMax2 = maxDp[2];
            int prevMin0 = minDp[0], prevMin1 = minDp[1], prevMin2 = minDp[2];

            // 최댓값 갱신
            maxDp[0] = a + Math.max(prevMax0, prevMax1);
            maxDp[1] = b + Math.max(prevMax0, Math.max(prevMax1, prevMax2));
            maxDp[2] = c + Math.max(prevMax1, prevMax2);

            // 최솟값 갱신
            minDp[0] = a + Math.min(prevMin0, prevMin1);
            minDp[1] = b + Math.min(prevMin0, Math.min(prevMin1, prevMin2));
            minDp[2] = c + Math.min(prevMin1, prevMin2);
        }

        // 결과 산출
        int finalMax = Math.max(maxDp[0], Math.max(maxDp[1], maxDp[2]));
        int finalMin = Math.min(minDp[0], Math.min(minDp[1], minDp[2]));

        System.out.println(finalMax + " " + finalMin);
        br.close();
    }
}
