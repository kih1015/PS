import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // [1단계] 컴퓨터 갯수 / 연결 갯수 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int computerCount = Integer.parseInt(br.readLine());
        int connectionCount = Integer.parseInt(br.readLine());

        // [2단계] 그래프 구성
        List<List<Integer>> computers = new ArrayList<>();
        for (int i = 0; i <= computerCount; i++) {
            computers.add(new ArrayList<>());
        }
        for (int i = 0; i < connectionCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            computers.get(second).add(first);
            computers.get(first).add(second);
        }

        // [3단계] 바이러스 걸린 컴퓨터 탐색
        boolean[] infected = new boolean[computerCount + 1];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        infected[1] = true;

        while (!stack.isEmpty()) {
            int curr = stack.pop();
            for (int computer : computers.get(curr)) {
                if (!infected[computer]) {
                    infected[computer] = true;
                    stack.push(computer);
                }
            }
        }

        // [4단계] 바이러스에 걸린 컴퓨터 갯수 새기
        int count = 0;
        for (int i = 2; i <= computerCount; i++) {
            if (infected[i]) {
                count++;
            }
        }

        // [4단계] 출력
        System.out.println(count);
        br.close();
    }
}
