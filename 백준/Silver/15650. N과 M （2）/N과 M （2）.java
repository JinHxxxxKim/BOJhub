import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int[] arr;
    public static int N;
    public static int R;

    public static int[] select;
    public static boolean[] isSelected;

    public static StringBuilder sb;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        for (int i = 1; i <= N; ++i) {
            arr[i] = i;
        }
        select = new int[R];
        isSelected = new boolean[N+1];
        sb = new StringBuilder();
        combination(0, 1);
    }

    private static void combination(int size, int idx) {
        if (size == R) {
            for (int i = 0; i < select.length; ++i) {
                sb.append(select[i] + " ");
            }
            System.out.println(sb.toString());
            sb = new StringBuilder();
            return;
        }
        for (int i = idx; i <= N; ++i) {
            if (isSelected[i])
                continue;
            isSelected[i] = true;
            select[size] = i;
            combination(size + 1, i + 1);
            isSelected[i] = false;
        }
    }
}
