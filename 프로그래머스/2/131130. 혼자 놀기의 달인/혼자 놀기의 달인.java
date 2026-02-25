import java.util.*;

class Solution {
    
    static int N;
    
    public int solution(int[] cards) {
        int answer = 1;
        
        N = cards.length;
        // 인접리스트 초기화
        List<Integer>[] adList = new ArrayList[N + 1];
        for(int idx = 1; idx <= N; ++idx) {
            adList[idx] = new ArrayList<Integer>();
        }
        for(int idx = 0; idx < N; ++idx) {
            int node1 = idx + 1;
            int node2 = cards[idx];
            // 탐색의 편의를 위해 양방향으로 설정(단, 시점과 종점이 다를 경우에 한하여) 
            adList[node1].add(node2);
            if(node1 != node2){
                adList[node2].add(node1);    
            }
        }
        
        // 모든 노드를 시작점으로 BFS 진행
        boolean[] visited = new boolean[N + 1];
        int groupCnt = 0;
        int grp1 = Integer.MIN_VALUE;
        int grp2 = Integer.MIN_VALUE;
        for(int node = 1; node <= N; ++node) {
            if(visited[node]) continue;
            ++groupCnt;
            Queue<Integer> q = new LinkedList<>();
            q.offer(node);
            visited[node] = true;
            int nodeCnt = 0;
            while(!q.isEmpty()) {
                int currNode = q.poll();
                ++nodeCnt;
                // 인접노드 탐색
                for(int nextNode : adList[currNode]) {
                    if(visited[nextNode]) continue;
                    visited[nextNode] = true;
                    q.offer(nextNode);
                }
            }
            // 최대 방문노드 수 갱신
            if(Math.min(grp1, grp2) < nodeCnt) {
                if(grp1 < grp2) {
                    grp1 = nodeCnt;
                } else {
                    grp2 = nodeCnt;
                }    
            }
            
        }
        
        if(groupCnt == 1) return 0;
        return grp1 * grp2;
    }
}