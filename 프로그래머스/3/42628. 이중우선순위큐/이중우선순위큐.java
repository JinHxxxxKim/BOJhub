import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {};
        
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());
        
        for(String ops : operations) {
            StringTokenizer st = new StringTokenizer(ops);  
            String op = st.nextToken();
            int num = Integer.parseInt(st.nextToken());
            switch(op){
                case "I":
                    minPQ.offer(num);
                    maxPQ.offer(num);
                    break;
                case "D":
                    if(num == -1){
                        // 큐에서 최솟값을 삭제합니다.
                        if(!minPQ.isEmpty()) {
                            int removeNum = minPQ.poll();
                            maxPQ.remove(removeNum);
                        }
                    }else{
                        // 큐에서 최댓값을 삭제합니다.
                        if(!maxPQ.isEmpty()) {
                            int removeNum = maxPQ.poll();
                            minPQ.remove(removeNum);
                        }
                    }
                    break;
            }
        }
        
        if(maxPQ.isEmpty()){
            return new int[] {0, 0};
        }else{
            return new int[] {maxPQ.poll(), minPQ.poll()};
        }
    }
}