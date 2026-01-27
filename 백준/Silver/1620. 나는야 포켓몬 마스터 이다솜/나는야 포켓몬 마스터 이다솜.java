import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<Integer, String> numberToString = new HashMap<>();
        Map<String, Integer> stringToNumber = new HashMap<>();

        for (int i = 0; i < N; i++) {
            int number = i + 1;
            String pokemon = br.readLine();

            numberToString.put(number, pokemon);
            stringToNumber.put(pokemon, number);
        }

        for (int i = 0; i < M; i++) {
            String query = br.readLine();

            if (Character.isDigit(query.charAt(0))) {
                sb.append(numberToString.get(Integer.parseInt(query)));
            } else {
                sb.append(stringToNumber.get(query));
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
