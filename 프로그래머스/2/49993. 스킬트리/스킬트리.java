import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        Set<Character> set = new HashSet<>();
        for(int idx = 0; idx < skill.length(); ++idx){
            set.add(skill.charAt(idx));
        }
        for(int idx = 0; idx < skill_trees.length; ++idx){
            String currSkillTree = skill_trees[idx];
            int rootIdx = 0;
            boolean possible = true;
            for(int skillIdx = 0; skillIdx < currSkillTree.length(); ++skillIdx){
                char currSkill = currSkillTree.charAt(skillIdx);
                
                // 선행 스킬이 필요한지 검증
                if(set.contains(currSkill)){
                    if(skill.charAt(rootIdx) == currSkill){
                        ++rootIdx;
                    }else{
                        possible = false;
                        break;
                    }
                }
            }
            if(possible)
                ++answer;
        }
        
        return answer;
    }
}