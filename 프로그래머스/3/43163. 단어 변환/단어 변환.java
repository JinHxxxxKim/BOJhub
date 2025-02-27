import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        boolean find = false;
        Set<String> chk = new HashSet<>();
        Map<String, Integer> dist = new HashMap<>();
        Queue<String> q = new ArrayDeque<>();
        q.offer(begin);
        chk.add(begin);
        dist.put(begin, 0);
        while(!q.isEmpty()) {
            String currStr = q.poll();
            if(currStr.equals(target)){
                break;
            }
            for(String chkWrd : words){
                if(chk.contains(chkWrd))
                    continue;
                int sameCnt = 0;
                for(int idx = 0; idx < chkWrd.length(); ++idx){
                    if(chkWrd.charAt(idx) == currStr.charAt(idx)){
                        ++sameCnt;
                    }
                }
                if(sameCnt == currStr.length() - 1){
                    q.offer(chkWrd);
                    dist.put(chkWrd, dist.get(currStr) + 1);
                    chk.add(chkWrd);
                }
            }
        }
        if(dist.containsKey(target)){
            return dist.get(target);
        }
        
        return 0;
    }
}