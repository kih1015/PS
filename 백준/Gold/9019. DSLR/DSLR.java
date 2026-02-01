import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // [1단계] 테스트 케이스 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        char[] opers = {'D', 'S', 'L', 'R'};
        boolean[] visited = new boolean[10000];
        char[] command = new char[10000];
        int[] parent = new int[10000];

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());

            Arrays.fill(visited, false);
            Queue<Integer> queue = new ArrayDeque<>();

            queue.offer(first);
            visited[first] = true;

            // [2단계] bfs
            while (!queue.isEmpty()) {
                Integer current = queue.poll();

                if (current == target) {
                    break;
                }

                for (char oper : opers) {
                    int next;

                    if (oper == 'D') {
                        next = (current * 2) % 10000;
                    } else if (oper == 'S') {
                        next = (current == 0) ? 9999 : current - 1;
                    } else if (oper == 'L') {
                        next = (current % 1000) * 10 + (current / 1000);
                    } else {
                        next = (current % 10) * 1000 + (current / 10);
                    }

                    if (!visited[next]) {
                        visited[next] = true;
                        command[next] = oper;
                        parent[next] = current;
                        queue.offer(next);
                    }
                }
            }

            // [3단계] 경로추적
            StringBuilder path = new StringBuilder();
            int temp = target;
            while (first != temp) {
                path.append(command[temp]);
                temp = parent[temp];
            }
            sb.append(path.reverse()).append("\n");
        }
        System.out.print(sb);
    }
}
