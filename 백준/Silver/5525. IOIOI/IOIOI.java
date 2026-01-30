import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        // [1단계] 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        String s = br.readLine();
        
        // [2단계] 슬라이딩 윈도우
        int count = 0;
        int match = 0;
        for (int i = 1; i < m - 1; i++) {
            boolean isMatched = s.charAt(i - 1) == 'I' && s.charAt(i) == 'O' && s.charAt(i + 1) == 'I';

            if (!isMatched) {
                match = 0;
                continue;
            }

            match++;
            if (match >= n) {
                count++;
            }
            i++;
        }

        // [3단계] 출력
        System.out.println(count);
    }
}
