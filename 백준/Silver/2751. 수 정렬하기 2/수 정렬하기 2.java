import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int N;
    static List<Integer> arr;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine().trim());
        arr = new ArrayList<>();
        for (int idx = 0; idx < N; ++idx) {
            arr.add(Integer.parseInt(br.readLine().trim()));
        }

        Collections.sort(arr);
        for (int num : arr) {
            sb.append(num).append("\n");
        }
        System.out.println(sb);

    }
}
