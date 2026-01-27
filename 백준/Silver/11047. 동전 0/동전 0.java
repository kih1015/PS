import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // 1. N, K 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int currentMoney = Integer.parseInt(st.nextToken());

        // 2. 동전 가치 입력
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            stack.push(Integer.parseInt(br.readLine()));
        }

        // 3. 동전 합 구하기 (그리디)
        int count = 0;
        while (currentMoney > 0) {
            int spending = stack.pop();
            count += (currentMoney / spending);
            currentMoney %= spending;
        }

        // 4. 출력
        System.out.println(count);
        br.close();
    }
}
