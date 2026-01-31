import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        // [1단계] 불기 년도 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int bulgiYear = Integer.parseInt(br.readLine());
        int seogiYear = bulgiYear - 543;

        // [2단계] 출력
        System.out.println(seogiYear);
    }
}
