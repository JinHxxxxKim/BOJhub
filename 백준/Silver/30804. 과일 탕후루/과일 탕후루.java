import java.io.*;
import java.util.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine().trim());
        int[] fruits = new int[N];
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < N; ++idx) {
            fruits[idx] = Integer.parseInt(st.nextToken());
        }

        // SOLUTION
        // r이 가리키는 과일은 반드시 포함한다.
        // 과일의 수가 2개를 넘는다면, l이 가리키는 과일을 제거한다.
        int l = 0;
        int r = 0;
        int ans = 0;
        Map<Integer, Integer> tangTang = new HashMap<>();

        while (r < N) {
            int currFruit = fruits[r];
            // 초기값 setting
            if (tangTang.isEmpty()) {
                tangTang.put(currFruit, 1);
                ans = Math.max(ans, r - l + 1);
                ++r;
                continue;
            }

            // 현재 추가하는 과일의 종류가 3번째 과일일 경우
            if (!tangTang.containsKey(currFruit) && tangTang.size() == 2) {
                while (tangTang.size() == 2) {
                    int removeFruit = fruits[l];
                    tangTang.put(removeFruit, tangTang.get(removeFruit) - 1);
                    if (tangTang.get(removeFruit) == 0) {
                        tangTang.remove(removeFruit);
                    }
                    ++l;
                }
            }
            if (tangTang.containsKey(currFruit)) {
                tangTang.put(currFruit, tangTang.get(currFruit) + 1);
            } else {
                tangTang.put(currFruit, 1);
            }
            ans = Math.max(ans, r - l + 1);
            ++r;
        }
        System.out.println(ans);
    }
}