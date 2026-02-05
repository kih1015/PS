import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<StringBuilder> str = pyramid(n);

        StringBuilder finalSb = new StringBuilder();
        for (StringBuilder sb : str) {
            finalSb.append(sb.toString()).append("\n");
        }

        System.out.print(finalSb);
        br.close();
    }

    private static List<StringBuilder> pyramid(int n) {
        if (n == 3) {
            List<StringBuilder> pyramid = new ArrayList<>();

            pyramid.add(new StringBuilder().append("  *  "));
            pyramid.add(new StringBuilder().append(" * * "));
            pyramid.add(new StringBuilder().append("*****"));

            return pyramid;
        }

        List<StringBuilder> subPyramid = pyramid(n / 2);

        for (int i = n / 2; i < n; i++) {
            String str = subPyramid.get(i - n / 2).toString();
            str = str + " " + str;
            subPyramid.add(new StringBuilder().append(str));
        }

        for (int i = 0; i < n / 2; i++) {
            subPyramid.get(i).insert(0, " ".repeat(n / 2));
            subPyramid.get(i).append(" ".repeat(n / 2));
        }

        return subPyramid;
    }
}
