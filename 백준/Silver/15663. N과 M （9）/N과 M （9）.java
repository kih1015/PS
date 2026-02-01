import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Main {
    private static StringBuilder builder;
    private static int[] result;
    private static int[] numbers;
    private static boolean[] visited;
    private static int n;
    private static int m;

    public static void main(String[] args) throws IOException {
        // [1단계] 테스트 케이스 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        builder = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        numbers = new int[n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numbers);

        result = new int[m];

        // [2단계] dfs
        dfs(0);
        System.out.println(builder);
        br.close();
    }

    private static void dfs(int depth) {
        if (depth == m) {
            StringJoiner sj = new StringJoiner(" ");
            for (int i : result) {
                sj.add(String.valueOf(i));
            }
            builder.append(sj).append("\n");
            return;
        }

        int lastUsed = -1;

        for (int i = 0; i < n; i++) {
            if (visited[i] || numbers[i] == lastUsed) {
                continue;
            }

            visited[i] = true;
            lastUsed = numbers[i];
            result[depth] = numbers[i];
            dfs(depth + 1);
            visited[i] = false;
        }
    }
}
