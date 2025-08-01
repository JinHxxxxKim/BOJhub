import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        // 개수가 많은 귤의 크기 먼저 담는다
        Map<Integer, Integer> map = new HashMap<>();
        for(int t : tangerine) {
            if(map.containsKey(t)){
                map.put(t, map.get(t) + 1);
            } else{
                map.put(t, 1);
            } 
        }
        
        Basket[] basket = new Basket[map.size()];
        int idx = 0;
        for(Integer key : map.keySet()) {
            basket[idx] = new Basket();
            basket[idx].size = key;
            basket[idx].cnt = map.get(key);
            ++idx;
        } 
        
        Arrays.sort(basket);
        int sum = 0;
        for(Basket b : basket){
            // System.out.println("=============");
            // System.out.println("SIZE: " + b.size);
            // System.out.println("CNT: " + b.cnt);
            sum += b.cnt;
            ++answer;
            if(sum >= k){
                break;
            }
        }
        
        // System.out.println(map);
        return answer;
    }
    
    static class Basket implements Comparable<Basket> {
        int size;
        int cnt;
        
        @Override
        public int compareTo(Basket m) {
            return m.cnt - this.cnt;
        }
        
    }
}