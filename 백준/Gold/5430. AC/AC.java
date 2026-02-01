import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // [1단계] m, n, h 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            String functions = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String elements = br.readLine().replace("[", "").replace("]", "");
            StringTokenizer st = new StringTokenizer(elements, ",");

            // [2단계] 배열 입력
            Deque<Integer> numbers = new ArrayDeque<>();
            while (st.hasMoreTokens()) {
                numbers.add(Integer.parseInt(st.nextToken()));
            }

            // [3단계] 연산 수행
            boolean isError = false;
            boolean isReversed = false;
            for (char oper : functions.toCharArray()) {
                if (oper == 'R') {
                    isReversed = !isReversed;
                    continue;
                }

                if (numbers.isEmpty()) {
                    isError = true;
                    break;
                }

                if (isReversed) {
                    numbers.removeLast();
                    continue;
                }

                numbers.removeFirst();
            }

            // [4단계] 출력 메시지 생성
            if (isError) {
                sb.append("error").append("\n");
                continue;
            }

            sb.append("[");
            while (!numbers.isEmpty()) {
                if (isReversed) {
                    sb.append(numbers.pollLast());
                } else {
                    sb.append(numbers.pollFirst());
                }

                if (!numbers.isEmpty()) {
                    sb.append(",");
                }
            }
            sb.append("]").append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}
