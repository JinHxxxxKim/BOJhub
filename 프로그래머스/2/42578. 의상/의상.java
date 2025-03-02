import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();
        for(String[] info : clothes){
            String key = info[1];
            if(map.containsKey(key)){
                map.put(key, map.get(key) + 1);
            }else{
                map.put(key, 1);
            }
        }
        
        int ans = 1;
        
        for(String key : map.keySet()){
            ans = ans * (map.get(key) + 1);
        }
        
        return ans - 1;
    }
}