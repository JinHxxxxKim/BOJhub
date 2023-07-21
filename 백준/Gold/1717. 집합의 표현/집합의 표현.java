import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int M;

    private static int[] rootInfo;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 원소 수
        M = Integer.parseInt(st.nextToken()); // 연산 수
        rootInfo = new int[N + 1];
        for (int i = 1; i <= N; ++i) {
            rootInfo[i] = i;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int operator = Integer.parseInt(st.nextToken());
            int set_1 = Integer.parseInt(st.nextToken());
            int set_2 = Integer.parseInt(st.nextToken());
            switch (operator) {
                case 0: //UNION
                    union(set_1, set_2);
                    break;
                case 1: // isSameSet ?
                    if (isSameSet(set_1, set_2)) {
                        sb.append("yes");
                        sb.append("\n");
                    } else {
                        sb.append("no");
                        sb.append("\n");
                    }
                    break;
            }
        }
        System.out.println(sb);
    }

    private static boolean isSameSet(int set1, int set2) {
        int set1_root = find(set1);
        int set2_root = find(set2);
        if (set1_root == set2_root) {
            return true;
        } else {
            return false;
        }
    }

    private static int find(int node) {
        if (node == rootInfo[node]) {
            return node;
        } else {
            return rootInfo[node] = find(rootInfo[node]);
        }
    }

    private static void union(int set1, int set2) {
        int set1_root = find(set1);
        int set2_root = find(set2);
        if (set1_root != set2_root) {
            if (set1_root < set2_root) {
                rootInfo[set2_root] = set1_root;
            } else {
                rootInfo[set1_root] = set2_root;
            }
        }
    }
}
