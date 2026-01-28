import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // [1단계] 랜선 갯수, 필요 랜선 수 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        // [2단계] 랜선 길이 입력 받기
        int[] ranStrings = new int[k];
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < k; i++) {
            ranStrings[i] = Integer.parseInt(br.readLine());
            if (ranStrings[i] > max) {
                max = ranStrings[i];
            }
        }

        // [3단계] 이분 탐색
        long left = 1;
        long right = max;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (getRanCount(ranStrings, mid) >= n) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // [4단계] 출력
        System.out.println(right);
        br.close();
    }

    private static long getRanCount(int[] ranStrings, long targetRanString) {
        long ranCount = 0;

        for (int ranString : ranStrings) {
            ranCount += ranString / targetRanString;
        }

        return ranCount;
    }
}
