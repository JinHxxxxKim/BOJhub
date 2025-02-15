import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        char[] array = br.readLine().trim().toCharArray();
        int N = array.length;
        int aCnt = 0;
        // 전체 a의 개수 파악
        for (char c : array) {
            if (c == 'a') {
                ++aCnt;
            }
        }

        // 슬라이딩 윈도우(l포함, r미포함)
        int l = 0;
        int r = aCnt;
        int maxAContain = Integer.MIN_VALUE;
        while (l < N) {
            int temp = 0;
            for (int cnt = 0; cnt < aCnt; ++cnt) {
                if (array[(l + cnt) % N] == 'a') {
                    temp++;
                }
            }
            if (temp > maxAContain) {
                maxAContain = temp;
            }
            ++l;
            r = (r + 1) % N;
        }
        System.out.println(aCnt - maxAContain);
    }
}
