import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int F;
    static Map<String, Integer> idxMap = new HashMap<>();
    static Map<Integer, Integer> parentMap = new HashMap();
    static Map<Integer, Integer> friendCnt = new HashMap();

    public static void main(String[] args) throws Exception {
        int TC = Integer.parseInt(br.readLine().trim());
        for (int testCase = 0; testCase < TC; ++testCase) {
            F = Integer.parseInt(br.readLine().trim());

            // SOLUTION
            int currIdx = 0;
            idxMap = new HashMap<>();
            parentMap = new HashMap<>();
            friendCnt = new HashMap<>();
            
            for(int cnt = 0; cnt < F; ++cnt) {
                st = new StringTokenizer(br.readLine().trim());
                String friend1 = st.nextToken();
                String friend2 = st.nextToken();

                // 처음 등장한 인원일 경우 초기값 setting
                if (!idxMap.containsKey(friend1)) {
                    idxMap.put(friend1, currIdx);
                    parentMap.put(currIdx, currIdx);
                    friendCnt.put(currIdx, 1);
                    ++currIdx;
                }
                if (!idxMap.containsKey(friend2)) {
                    idxMap.put(friend2, currIdx);
                    parentMap.put(currIdx, currIdx);
                    friendCnt.put(currIdx, 1);
                    ++currIdx;
                }

                // 두 사람이 동일 집합에 속해있는지 여부 판단 필요
                int friend1Root = find(idxMap.get(friend1));
                int friend2Root = find(idxMap.get(friend2));

                // 동일 집합에 속해있다면,
                if (friend1Root == friend2Root) {
                    sb.append(friendCnt.get(friend1Root)).append("\n");
                }

                // 동일 집합에 속해있지 않다면, 두 집합을 합치면서 친구네트워크 수를 갱신한다.
                else {
                    union(friend1Root, friend2Root);
                    sb.append(friendCnt.get(friend1Root)).append("\n");
                }
            }
        }
        System.out.println(sb);
    }

    static void union(int node1Root, int node2Root) {
        int node1FriendCnt = friendCnt.get(node1Root);
        int node2FriendCnt = friendCnt.get(node2Root);
        int total = node1FriendCnt + node2FriendCnt;

        friendCnt.put(node1Root, total);
        friendCnt.put(node2Root, total);

        if (node1Root < node2Root) {
            parentMap.put(node2Root, node1Root);
        } else {
            parentMap.put(node1Root, node2Root);
        }
    }

    static int find(int node) {
        if (node == parentMap.get(node)) {
            return node;
        } else {
            parentMap.put(node, find(parentMap.get(node)));
            return parentMap.get(node);
        }
    }
}


