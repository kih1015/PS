import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // 테스트 케이스 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        // bfs 완전탐색
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(a);

        int level = 0;
        boolean found = false;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int current = queue.poll();

                if (current == b) {
                    found = true;
                    break;
                }

                int next1 = current * 2;
                long next2 = current * 10L + 1;

                if (next1 <= b) {
                    queue.offer(next1);
                }
                if (next2 <= b) {
                    queue.offer((int) next2);
                }
            }

            level++;
            if (found) {
                break;
            }
        }

        // 최소 연산 + 1 출력
        if (!found) {
            level = -1;
        }
        System.out.println(level);
        br.close();
    }
}
