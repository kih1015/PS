import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static class Term {
        int data;
        int index;

        public Term(int data, int index) {
            this.data = data;
            this.index = index;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Integer[] a = new Integer[n + 1];

        PriorityQueue<Term> pqA = new PriorityQueue<>((t1, t2) -> {
            if (t1.data == t2.data) {
                return t1.index - t2.index;
            }
            return t2.data - t1.data;
        });

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            pqA.add(new Term(a[i], i));
        }

        int m = Integer.parseInt(br.readLine());
        Integer[] b = new Integer[m + 1];

        PriorityQueue<Term> pqB = new PriorityQueue<>((t1, t2) -> {
            if (t1.data == t2.data) {
                return t1.index - t2.index;
            }
            return t2.data - t1.data;
        });

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
            pqB.add(new Term(b[i], i));
        }

        List<Integer> seq = new ArrayList<>();

        int fromA = 1;
        int fromB = 1;

        while (true) {
            Term[] max = getMax(pqA, pqB, fromA, fromB);

            if (max == null) {
                break;
            }

            seq.add(max[0].data);

            fromA = max[0].index + 1;
            fromB = max[1].index + 1;
        }

        System.out.println(seq.size());

        StringBuilder sb = new StringBuilder();
        for (int value : seq) {
            sb.append(value).append(" ");
        }
        System.out.print(sb);

        br.close();
    }

    private static Term[] getMax(PriorityQueue<Term> pqA, PriorityQueue<Term> pqB, int fromA, int fromB) {
        while (!pqA.isEmpty()) {
            Term maxA = pqA.poll();

            if (maxA.index < fromA) {
                continue;
            }

            while (!pqB.isEmpty()) {
                Term maxB = pqB.peek();

                if (maxB.index < fromB) {
                    pqB.poll();
                    continue;
                }

                if (maxA.data < maxB.data) {
                    pqB.poll();
                    continue;
                }

                if (maxA.data == maxB.data) {
                    return new Term[]{maxA, maxB};
                }

                break;
            }
        }

        return null;
    }
}
