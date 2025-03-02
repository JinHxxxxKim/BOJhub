import java.util.*;

class Solution {
    static boolean ans;
    
    public boolean solution(String[] phone_book) {
        ans = true;
        // 첫 번째 자리 수
        for(int num = 0; num < 10; ++num){
            Set<String> set = new HashSet<>();
            for(String phone : phone_book){
                if(phone.charAt(0) - '0' == num)
                    set.add(phone);
            }
            sol(1, set);
        }
        return ans;
    }
    
    // set: count 번째 자리 수를 기준으로 분류완료.
    static void sol(int count, Set<String> set){
        if(set.isEmpty() || set.size() == 1) return;
        // System.out.println("COUNT: " + count + " SET: " + set);
        for(String phone : set){
            if(phone.length() == count){
                ans = false;
                return;
            }
        }
        
        for(int num = 0; num < 10; ++num){
            Set<String> nextSet = new HashSet<>();
            for(String phone : set){
                if(phone.charAt(count) - '0' == num)
                    nextSet.add(phone);
            }
            sol(count + 1, nextSet);
        }
    }
}