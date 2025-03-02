import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int limit = nums.length / 2;
        Set<Integer> set = new HashSet<>();
        for(int poketmon : nums){
            set.add(poketmon);
        }
        return Math.min(limit, set.size());
    }
}