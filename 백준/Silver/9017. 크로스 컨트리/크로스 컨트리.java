import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int TC = Integer.parseInt(br.readLine().trim());
        for (int testCase = 0; testCase < TC; ++testCase) {
            int N = Integer.parseInt(br.readLine().trim());
            int[] goal = new int[N];
            st = new StringTokenizer(br.readLine().trim());
            for (int idx = 0; idx < N; ++idx) {
                goal[idx] = Integer.parseInt(st.nextToken());
            }
            int score = 1;
            // 점수계산에 포함되지 않을 팀을 구한다.
            Set<Integer> excludeTeam = new HashSet<>();
            Map<Integer, Integer> teamCnt = new HashMap<>();
            for (int idx = 0; idx < N; ++idx) {
                int currTeam = goal[idx];
                if (teamCnt.containsKey(currTeam)) {
                    teamCnt.put(currTeam, teamCnt.get(currTeam) + 1);
                }else{
                    teamCnt.put(currTeam, 1);
                }
            }

//            System.out.println("teamCnt = " + teamCnt);
            // 6명이 없다면 제외한다.
            for (Integer teamNum : teamCnt.keySet()) {
                if(teamCnt.get(teamNum) < 6){
                    excludeTeam.add(teamNum);
                }
            }
//            System.out.println("excludeTeam = " + excludeTeam);

            // 점수 계산
            Map<Integer, Integer> teamScore = new HashMap<>();
            teamCnt = new HashMap<>();
            for (int idx = 0; idx < N; ++idx) {
                int currTeam = goal[idx];
//                System.out.println("score = " + score);
                // 자격이 없는 팀은 제외
                if (excludeTeam.contains(currTeam)) {
                    continue;
                }

                if (teamScore.containsKey(currTeam) && teamCnt.get(currTeam) < 4) {
                    teamScore.put(currTeam, teamScore.get(currTeam) + score);
                    teamCnt.put(currTeam, teamCnt.get(currTeam) + 1);
                } else if(!teamScore.containsKey(currTeam)){
                    teamScore.put(currTeam, score);
                    teamCnt.put(currTeam, 1);
                }
                ++score;
//                System.out.println("teamCnt = " + teamCnt);
//                System.out.println("teamScore = " + teamScore);

            }

            // 우승자 계산
            int ans = -1;
//            System.out.println("teamScore = " + teamScore);
            Set<Integer> sameScore = new HashSet<>();
            int maxScore = Integer.MAX_VALUE;
            for (int teamNum : teamScore.keySet()) {
                if(teamScore.get(teamNum) < maxScore){
                    sameScore.clear();
                    sameScore.add(teamNum);
                    ans = teamNum;
                    maxScore = teamScore.get(teamNum);
                } else if (teamScore.get(teamNum).equals(maxScore)) {
                    sameScore.add(teamNum);
                    ans = teamNum;
                }
            }

//            System.out.println("sameScore = " + sameScore);
            // 동점자 계산
            if (sameScore.size() != 1) {
                // 5등 계산
                Map<Integer, Integer> seq = new HashMap<>();
                for (int idx = 0; idx < N; ++idx) {
                    int currTeam = goal[idx];
                    if (sameScore.contains(currTeam)) {
                        if (seq.containsKey(currTeam)) {
                            seq.put(currTeam, seq.get(currTeam) + 1);
                        }else{
                            seq.put(currTeam, 1);
                        }

                        if (seq.get(currTeam).equals(5)) {
                            ans = currTeam;
                            break;
                        }
                    }
                }
            }

            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
