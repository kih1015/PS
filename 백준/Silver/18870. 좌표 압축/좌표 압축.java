import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // [1단계] 좌표의 개수(n) 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // [2단계] 좌표 입력 받기
        int[] xn = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            xn[i] = Integer.parseInt(st.nextToken());
        }

        // [3단계] 중복 제거 및 정렬
        int[] sortedXn = Arrays.stream(xn)
                .distinct()
                .sorted()
                .toArray();

        // [4단계] 압축 좌표 계산
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int rank = Arrays.binarySearch(sortedXn, xn[i]);
            sb.append(rank).append(" ");
        }
        
        // [5단계] 출력
        System.out.println(sb);
        br.close();
    }
}
