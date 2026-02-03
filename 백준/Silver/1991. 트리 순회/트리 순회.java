import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // 노드 갯수 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 트리 구성
        int[][] tree = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int current = st.nextToken().charAt(0) - 'A';
            int left = st.nextToken().charAt(0) - 'A';
            int right = st.nextToken().charAt(0) - 'A';

            tree[current][0] = left;
            tree[current][1] = right;
        }

        // 트리 순회
        preorder(0, tree);
        System.out.println();
        inorder(0, tree);
        System.out.println();
        postorder(0, tree);
        br.close();
    }

    private static void preorder(int node, int[][] tree) {
        if (node == '.' - 'A') {
            return;
        }

        System.out.print((char) (node + 'A'));
        preorder(tree[node][0], tree);
        preorder(tree[node][1], tree);
    }

    private static void inorder(int node, int[][] tree) {
        if (node == '.' - 'A') {
            return;
        }

        inorder(tree[node][0], tree);
        System.out.print((char) (node + 'A'));
        inorder(tree[node][1], tree);
    }

    private static void postorder(int node, int[][] tree) {
        if (node == '.' - 'A') {
            return;
        }

        postorder(tree[node][0], tree);
        postorder(tree[node][1], tree);
        System.out.print((char) (node + 'A'));
    }
}
