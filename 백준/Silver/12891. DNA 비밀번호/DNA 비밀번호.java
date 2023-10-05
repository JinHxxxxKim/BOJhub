import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        char[] array = br.readLine().toCharArray();
        int[] cntDNA = new int[4];

        int[] limit = new int[4];
        st = new StringTokenizer(br.readLine());
        limit[0] = Integer.parseInt(st.nextToken());
        limit[1] = Integer.parseInt(st.nextToken());
        limit[2] = Integer.parseInt(st.nextToken());
        limit[3] = Integer.parseInt(st.nextToken());

        int start = 0;
        int end = start + (P-1);

        for (int i = start; i <= end; ++i) {
            switch (array[i]) {
                case 'A':
                    cntDNA[0]++;
                    break;
                case 'C':
                    cntDNA[1]++;
                    break;
                case 'G':
                    cntDNA[2]++;
                    break;
                case 'T':
                    cntDNA[3]++;
                    break;
            }
        }

        int ans = 0;
        while (true) {
            if (cntDNA[0] >= limit[0] && cntDNA[1] >= limit[1] && cntDNA[2] >= limit[2] && cntDNA[3] >= limit[3]) {
                ans++;
            }

            if (end == S-1) {
                break;
            }

            switch (array[start]) {
                case 'A':
                    cntDNA[0]--;
                    break;
                case 'C':
                    cntDNA[1]--;
                    break;
                case 'G':
                    cntDNA[2]--;
                    break;
                case 'T':
                    cntDNA[3]--;
                    break;
            }
            start++;

            switch (array[end+1]) {
                case 'A':
                    cntDNA[0]++;
                    break;
                case 'C':
                    cntDNA[1]++;
                    break;
                case 'G':
                    cntDNA[2]++;
                    break;
                case 'T':
                    cntDNA[3]++;
                    break;
            }
            end++;
        }
        System.out.println(ans);
    }
}
