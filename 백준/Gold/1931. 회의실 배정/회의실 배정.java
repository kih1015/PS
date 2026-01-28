import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // [1단계] 회의의 수(n) 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // [2단계] 회의 시간표 입력받기
        int[][] timetable = new int[n][2];
        for (int i = 0; i < timetable.length; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());

            timetable[i][0] = startTime;
            timetable[i][1] = endTime;
        }

        // [3단계] 시간표 정렬
        Arrays.sort(timetable, (a, b) -> {
            if (a[1] == b[1]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        // [4단계] 그리디
        int currentTime = 0;
        int count = 0;

        for (int i = 0; i < timetable.length; i++) {
            if (timetable[i][0] < currentTime) {
                continue;
            }

            currentTime = timetable[i][1];
            count++;
        }

        // [5단계] 출력
        System.out.println(count);
    }
}
