import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static char gameType;

    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        gameType = st.nextToken().charAt(0);

        int ans = 0;
        Set<String> done = new HashSet(); // 임스와 이미 게임을 진행한 사람들
        Set<String> waiting = new HashSet<>(); // 임스와 게임을 진행하기 위해 대기하는 사람들
        int reqPeople = 0;

        // 게임 참여 인원 설정
        switch (gameType) {
            case 'Y':
                reqPeople = 1;
                break;
            case 'F':
                reqPeople = 2;
                break;
            case 'O':
                reqPeople = 3;
                break;
        }

        for (int cnt = 0; cnt < N; ++cnt) {
            String currGamer = br.readLine().trim();
            // 이미 게임을 진행한 인원인지 검증
            if (done.contains(currGamer)) {
                continue;
            }
            // 게임을 진행한 적이 없을 경우, 대기 명단에 저장
            waiting.add(currGamer);

            // 대기인원의 수가 게임 진행 이눵 수와 동일하다면 게임 진행
            if (waiting.size() == reqPeople) {
                for (String gamer : waiting) {
                    done.add(gamer);
                }
                // 후처리
                ++ans;
                waiting.clear();
            }
        }
        System.out.print(ans);
    }
}
