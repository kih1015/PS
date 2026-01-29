import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // [1단계] 집터 크기, 블럭 개수 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // [2단계] 탕후루 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            int item = Integer.parseInt(st.nextToken());

            array[i] = item;
        }

        // [3단계] 투 포인터 탐색
        int max = Integer.MIN_VALUE;
        int kind = 0;
        int[] counter = new int[9 + 1];
        int i = 0, j = 0;

        while (true) {
            if (kind <= 2) {
                max = Math.max(max, j - i);

                if (j == n) {
                    break;
                }

                int fruitKind = array[j++];
                if (counter[fruitKind]++ == 0) {
                    kind++;
                }

                continue;
            }

            int fruitKind = array[i++];
            if (counter[fruitKind]-- == 1) {
                kind--;
            }
        }

        // [4단계] 출력
        System.out.println(max);
        br.close();
    }
}
