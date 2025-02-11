import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        int TC = Integer.parseInt(br.readLine().trim());
        for(int testCast = 0; testCast < TC; ++testCast){
            st = new StringTokenizer(br.readLine().trim());
            sb.append(st.nextToken() + " ");
            int ans = 0;

            int[] height = new int[20];
            Arrays.fill(height, 0);
            for(int idx = 0; idx < 20; ++idx){
                int currHeight = Integer.parseInt(st.nextToken());

                // 1. 자신보다 키큰 아이를 찾는다. (찾는다 or 못찾는다)
                boolean find = false;
                int tallerIdx = -1;
                for(int chkIdx = 0; chkIdx < 20; ++chkIdx){
                    if(height[chkIdx] == 0){
                        tallerIdx = chkIdx;
                        break;
                    }
                    if(height[chkIdx] > currHeight){
                        tallerIdx = chkIdx;
                        find = true;
                        break;
                    }
                }
                // 2. 해당 아이부터 끝까지 뒤로 민다.
                if(find){
                    for(int tempIdx = 19; tempIdx > tallerIdx; --tempIdx){
                        if(height[tempIdx -1] != 0){
                            ++ans;
                        }
                        height[tempIdx] = height[tempIdx -1];
                    }
                }
                // 3. 빈 자리에 들어간다.
                height[tallerIdx] = currHeight;
            }
            sb.append(ans+"\n");
        }
        System.out.print(sb);
    }
}
