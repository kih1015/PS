import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Main {
    private static StringBuilder builder;
    private static int n;
    private static int m;

    public static void main(String[] args) throws IOException {
        // [1단계] 테스트 케이스 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        builder = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // [2단계] dfs
        dfs(0, new LinkedList<>());
        System.out.println(builder);
        br.close();
    }

    private static void dfs(int current, List<Integer> list) {
        if (list.size() == m) {
            StringJoiner sj = new StringJoiner(" ");
            for (int i : list) {
                sj.add(String.valueOf(i));
            }
            builder.append(sj).append("\n");
            return;
        }

        for (int i = current + 1; i <= n; i++) {
            list.add(i);
            dfs(i, list);
            list.remove(list.size() - 1);
        }
    }
}
