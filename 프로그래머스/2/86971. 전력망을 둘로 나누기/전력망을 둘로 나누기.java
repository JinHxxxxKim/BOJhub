// 완전탐색?
// 모든 선을 끊어보며 BFS 2번씩 돌리기

import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        int answer = 100;
        
        List<Integer>[] adList = new ArrayList[n + 1];
        for(int idx = 1; idx <= n; ++idx){
            adList[idx] = new ArrayList<Integer>();
        }
        
        for(int idx = 0; idx < wires.length; ++idx){
            int node1 = wires[idx][0];
            int node2 = wires[idx][1];
            // 양방향 간선
            adList[node1].add(node2);
            adList[node2].add(node1);
        }
        
        System.out.println(Arrays.toString(adList));
        // 로직 시작
        
        // 모든 간선 한번씩 끊어보며 완전 탐색 2회 실행
        for(int idx = 0; idx < wires.length; ++idx){
            // 끊을 간선
            int node1 = wires[idx][0];
            int node2 = wires[idx][1];
            
            boolean[] visited = new boolean[n + 1];
            
            int bfsCnt = 0;
            int nodeCnt1 = 0;
            int nodeCnt2 = 0;
            
            // 모든 node를 시작점으로 하여 BFS 실행
            for(int nodeNum = 1; nodeNum <= n; ++nodeNum) {
                if(visited[nodeNum]) continue;
                
                Queue<Integer> q = new LinkedList<>();
                q.offer(nodeNum);
                visited[nodeNum] = true;
                while(!q.isEmpty()) {
                    int currNode = q.poll();
                    
                    // 방문 노드 Count
                    if(bfsCnt == 0) ++nodeCnt1;
                    else ++nodeCnt2;
                    
                    for(int nextNode : adList[currNode]){
                        // 끊긴 간선 검증
                        if((currNode == node1 && nextNode == node2) 
                           ||
                           (currNode == node2 && nextNode == node1) 
                          ){
                            continue;
                        }
                        
                        // 방문검증
                        if(visited[nextNode]) continue;
                        
                        visited[nextNode] = true;
                        q.offer(nextNode);
                    }
                }
                
                // 1차 BFS 종료
                ++bfsCnt;
            }
            // 간선마다 확인 완료 시 answer(최소값) 갱신
            answer = Math.min(answer, Math.abs(nodeCnt1 - nodeCnt2));
        }
        
        
        return answer;
    }
}