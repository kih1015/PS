import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(n);

        int[] time = new int[100000 + 1];
        Arrays.fill(time, -1);
        time[n] = 0;

        int count = 0;
        int minTime = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            if (curr == k) {
                minTime = time[curr];
                count++;
                continue;
            }

            if (time[curr] >= minTime) {
                continue;
            }

            int[] nextNodes = {curr - 1, curr + 1, curr * 2};
            for (int next : nextNodes) {
                if (!isBound(next)) {
                    continue;
                }

                if (time[next] == -1 || time[next] == time[curr] + 1) {
                    time[next] = time[curr] + 1;
                    queue.offer(next);
                }
            }
        }

        System.out.println(minTime);
        System.out.println(count);

        br.close();
    }

    private static boolean isBound(int node) {
        return 0 <= node && node <= 100000;
    }
}
